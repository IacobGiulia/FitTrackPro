package ro.fittrack.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.fittrack.auth.entity.User;
import ro.fittrack.auth.repository.UserRepository;
import ro.fittrack.auth.security.JwtService;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        System.out.println("=== JWT FILTER ===");
        System.out.println("URI: " + request.getRequestURI());
        System.out.println("AUTH HEADER: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("NICIUN HEADER BEARER -> trece neautentificat");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        boolean valid = jwtService.isTokenValid(token);
        System.out.println("TOKEN VALID: " + valid);

        if (!valid) {
            System.out.println("TOKEN INVALID -> trece neautentificat");
            filterChain.doFilter(request, response);
            return;
        }

        String email = jwtService.extractEmail(token);
        System.out.println("EMAIL DIN TOKEN: " + email);

        User user = userRepository.findByEmail(email)
                .orElse(null);

        System.out.println("USER GASIT IN DB: " + (user != null));

        if (user != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            Collections.emptyList()
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            System.out.println("AUTHENTICATION SETATA CU SUCCES");
        } else {
            System.out.println("AUTHENTICATION NU A FOST SETATA (user null sau deja exista una)");
        }

        filterChain.doFilter(request, response);
    }
}
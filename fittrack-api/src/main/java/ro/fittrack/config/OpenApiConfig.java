package ro.fittrack.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fitTrackerOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                    .title("FitTrack Pro API")
                    .description("Rest API for FitTrack Pro")
                    .version("1.0.0")
                    .contact(new Contact()
                            .name("Giulia Iacob"))
                    .license(new License()
                            .name("MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation"));
    }
}
package ro.fittrack.auth.mapper;

import org.mapstruct.Mapper;
import ro.fittrack.auth.dto.RegisterRequest;
import ro.fittrack.auth.dto.RegisterResponse;
import ro.fittrack.auth.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterRequest request);

    RegisterResponse toResponse(User user);

}
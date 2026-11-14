package team.anonyms.athletelink.profileservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserControllerDto;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserPublicControllerDto;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserToUpdateControllerDto;
import team.anonyms.athletelink.profileservice.dto.service.user.UserPublicServiceDto;
import team.anonyms.athletelink.profileservice.dto.service.user.UserServiceDto;
import team.anonyms.athletelink.profileservice.dto.service.user.UserToUpdateServiceDto;
import team.anonyms.athletelink.profileservice.entity.User;
import team.anonyms.athletelink.profileservice.service.UserService;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = UserService.class)
public interface UserMapper {
    UserControllerDto userServiceToControllerDto(UserServiceDto dto);

    UserPublicControllerDto userPublicServiceToControllerDto(UserPublicServiceDto dto);

    List<UserPublicControllerDto> userPublicServiceToControllerDtoList(List<UserPublicServiceDto> users);

    default Page<UserPublicControllerDto> userPublicServiceToControllerDtoPage(Page<UserPublicServiceDto> users) {
        return users.map(this::userPublicServiceToControllerDto);
    }

    UserToUpdateServiceDto userToUpdateControllerToServiceDto(UUID userId, UserToUpdateControllerDto dto);

    @Mapping(target = "age", expression = "java(UserService.countUserAge(user))")
    UserPublicServiceDto userEntityToUserPublicServiceDto(User user);
}

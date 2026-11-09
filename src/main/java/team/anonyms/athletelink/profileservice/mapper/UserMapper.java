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

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserControllerDto userServiceToControllerDto(UserServiceDto dto);

    Page<UserPublicControllerDto> userPublicServiceToControllerDtoList(Page<UserPublicServiceDto> users);

    UserToUpdateServiceDto userToUpdateControllerToServiceDto(UUID userId, UserToUpdateControllerDto dto);

    @Mapping(target = "age", expression = "java(UserService.countUserAge(user))")
    UserPublicServiceDto userEntityToUserPublicServiceDto(User user);
}

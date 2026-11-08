package team.anonyms.athletelink.profileservice.mapper;

import org.mapstruct.Mapper;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserControllerDto;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserPublicControllerDto;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserToUpdateControllerDto;
import team.anonyms.athletelink.profileservice.dto.service.user.UserPublicServiceDto;
import team.anonyms.athletelink.profileservice.dto.service.user.UserServiceDto;
import team.anonyms.athletelink.profileservice.dto.service.user.UserToUpdateServiceDto;

import java.util.List;
import java.util.UUID;

@Mapper
public interface UserMapper {
    UserControllerDto userServiceToControllerDto(UserServiceDto dto);

    List<UserPublicControllerDto> userPublicServiceToControllerDtoList(List<UserPublicServiceDto> users);

    UserToUpdateServiceDto userToUpdateControllerToServiceDto(UUID userId, UserToUpdateControllerDto dto);
}

package team.anonyms.athletelink.profileservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserControllerDto;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserPublicControllerDto;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserToUpdateControllerDto;
import team.anonyms.athletelink.profileservice.mapper.UserMapper;
import team.anonyms.athletelink.profileservice.service.UserService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me")
    public UserControllerDto me(@RequestHeader UUID userId) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "GET /user/me/{userId}")
                .addKeyValue("userId", userId)
                .log();

        return userMapper.userServiceToControllerDto(userService.me(userId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserPublicControllerDto> getUsersByUserIds(@RequestParam("userIds") List<UUID> userIds) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "GET user?userIds")
                .addKeyValue("userIds", userIds)
                .log();

        return userMapper.userPublicServiceToControllerDtoList(userService.getUsersByUserIds(userIds));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateUser(@RequestHeader("X-User-Id") UUID userId, @RequestBody @Valid UserToUpdateControllerDto dto) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "PUT /user/update-user/{userId}")
                .addKeyValue("userId", userId)
                .addKeyValue("userToUpdate", dto)
                .log();

        userService.updateUser(userMapper.userToUpdateControllerToServiceDto(userId, dto));
    }

}

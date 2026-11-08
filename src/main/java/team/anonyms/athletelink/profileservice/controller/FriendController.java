package team.anonyms.athletelink.profileservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserPublicControllerDto;
import team.anonyms.athletelink.profileservice.mapper.UserMapper;
import team.anonyms.athletelink.profileservice.service.FriendService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/friend")
public class FriendController {
    private final FriendService friendService;
    private final UserMapper userMapper;

    //TODO: add pagination
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserPublicControllerDto> getPaginatedFriendsByUserId(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody UUID userToGetFriends,
            @RequestParam int page,
            @RequestParam int size
    ) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "GET /friend?page=,size=")
                .addKeyValue("userId", userId)
                .addKeyValue("userToGetFriends", userToGetFriends)
                .addKeyValue("page", page)
                .addKeyValue("size", size)
                .log();

        return userMapper.userPublicServiceToControllerDtoList(
                friendService.getPaginatedFriendsByUserId(userId, page, size)
        );
    }
}

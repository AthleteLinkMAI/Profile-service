package team.anonyms.athletelink.profileservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.anonyms.athletelink.profileservice.dto.controller.user.UserPublicControllerDto;
import team.anonyms.athletelink.profileservice.mapper.UserMapper;
import team.anonyms.athletelink.profileservice.service.FriendService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/friend")
public class FriendController {
    private final FriendService friendService;
    private final UserMapper userMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userToGetFriends}")
    public Page<UserPublicControllerDto> getPaginatedFriendsByUserId(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID userToGetFriends,
            @ParameterObject @PageableDefault(size = 20, sort = "nickname") Pageable pageable
    ) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "GET /friend/{userToGetFriends}")
                .addKeyValue("userId", userId)
                .addKeyValue("userToGetFriends", userToGetFriends)
                .addKeyValue("pageable", pageable)
                .log();

        return userMapper.userPublicServiceToControllerDtoList(
                friendService.getPaginatedFriendsByUserId(userToGetFriends, pageable)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteFriend(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody UUID friendToDeleteId
    ) {
        log.atInfo()
                .setMessage("API endpoint")
                .addKeyValue("API endpoint", "DELETE /friend")
                .addKeyValue("userId", userId)
                .addKeyValue("friendToDeleteId", friendToDeleteId)
                .log();

        friendService.deleteFriend(userId, friendToDeleteId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/request/incoming/{userToGetFriendRequests}")
    public Page<UserPublicControllerDto> getPaginatedIncomingFriendsRequestsByUserId(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID userToGetFriendRequests,
            @ParameterObject @PageableDefault(size = 20, sort = "nickname") Pageable pageable
    ) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "GET /friend/request/incoming/{userToGetFriendRequests}")
                .addKeyValue("userId", userId)
                .addKeyValue("userToGetFriendRequests", userToGetFriendRequests)
                .addKeyValue("pageable", pageable)
                .log();

        return userMapper.userPublicServiceToControllerDtoList(
                friendService.getPaginatedIncomingFriendRequestsByUserId(userToGetFriendRequests, pageable)
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/request/outgoing/{userToGetFriendRequests}")
    public Page<UserPublicControllerDto> getPaginatedOutgoingFriendsRequestsByUserId(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID userToGetFriendRequests,
            @ParameterObject @PageableDefault(size = 20, sort = "nickname") Pageable pageable
    ) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "GET /friend/request/outcoming/{userToGetFriendRequests}")
                .addKeyValue("userId", userId)
                .addKeyValue("userToGetFriendRequests", userToGetFriendRequests)
                .addKeyValue("pageable", pageable)
                .log();

        return userMapper.userPublicServiceToControllerDtoList(
                friendService.getPaginatedOutcomingFriendRequestsByUserId(userToGetFriendRequests, pageable)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/request")
    public void sendFriendRequest(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody UUID userToSendFriendRequestId
    ) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "POST /friend/request")
                .addKeyValue("userId", userId)
                .addKeyValue("userToSendFriendRequestId", userToSendFriendRequestId)
                .log();

        friendService.sendFriendRequest(userId, userToSendFriendRequestId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/request/accept")
    public void acceptFriendRequest(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody UUID pendingFriendId
    ) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "POST /friend/request/accept")
                .addKeyValue("userId", userId)
                .addKeyValue("pendingFriendId", pendingFriendId)
                .log();

        friendService.acceptFriendRequest(userId, pendingFriendId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/request/decline")
    public void declineFriendRequest(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody UUID pendingFriendId
    ) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "POST /friend/request/decline")
                .addKeyValue("userId", userId)
                .addKeyValue("pendingFriendId", pendingFriendId)
                .log();

        friendService.declineFriendRequest(userId, pendingFriendId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/request")
    public void revokeFriendRequest(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody UUID futureFriendId
    ) {
        log.atInfo()
                .setMessage("API endpoint is called")
                .addKeyValue("API endpoint", "DELETE /friend/request")
                .addKeyValue("userId", userId)
                .addKeyValue("futureFriendId", futureFriendId)
                .log();

        friendService.revokeFriendRequest(userId, futureFriendId);
    }
}

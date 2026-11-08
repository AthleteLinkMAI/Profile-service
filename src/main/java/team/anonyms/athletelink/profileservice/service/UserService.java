package team.anonyms.athletelink.profileservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.anonyms.athletelink.profileservice.dto.service.user.UserPublicServiceDto;
import team.anonyms.athletelink.profileservice.dto.service.user.UserServiceDto;
import team.anonyms.athletelink.profileservice.dto.service.user.UserToUpdateServiceDto;
import team.anonyms.athletelink.profileservice.entity.User;
import team.anonyms.athletelink.profileservice.exception.EntityAlreadyExistsException;
import team.anonyms.athletelink.profileservice.repository.UserRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public static Integer countUserAge(User user) {
        return Period.between(user.getBirthdate(), LocalDate.now()).getYears();
    }

    public UserServiceDto me(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("User was not found; id=" + userId)
        );

        return new UserServiceDto(
                userId,
                user.getRole(),
                user.getNickname(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getIsVerified(),
                countUserAge(user),
                user.getRegistrationDate()
        );
    }

    public List<UserPublicServiceDto> getUsersByUserIds(List<UUID> userIds) {
        List<UserPublicServiceDto> usersToReturn = new LinkedList<>();

        List<User> usersFound = userRepository.findAllById(userIds);
        for (User user : usersFound) {
            usersToReturn.add(
                    new UserPublicServiceDto(
                            user.getId(),
                            user.getRole(),
                            user.getNickname(),
                            user.getName(),
                            user.getSurname(),
                            countUserAge(user),
                            user.getRegistrationDate()
                    )
            );
        }

        return usersToReturn;
    }

    public void updateUser(UserToUpdateServiceDto dto) {
        User userToUpdate = userRepository.findById(dto.userId()).orElseThrow(
                () -> new EntityNotFoundException("User not found; id=" + dto.userId())
        );

        if (userRepository.findByNickname(dto.nickname()).isPresent()) {
            throw new EntityAlreadyExistsException(
                    "User with this nickname already exists; nickname=" + dto.nickname()
            );
        }

        userToUpdate.setNickname(dto.nickname());
        userToUpdate.setName(dto.name());
        userToUpdate.setSurname(dto.surname());
        userToUpdate.setBirthdate(dto.birthdate());

        userRepository.save(userToUpdate);
    }
}

package com.wsmartsystem.usereditor.user.service;

import com.wsmartsystem.usereditor.user.dao.UserRepository;
import com.wsmartsystem.usereditor.user.domain.User;
import com.wsmartsystem.usereditor.user.exception.UserEntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

@Log4j2
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> createUser(final User createUser) {
        createUser.setId(null);
        return Mono.just(userRepository.save(createUser))
                .doOnSuccess(createdUser -> log.info("Created user with id.: {}", createdUser.getId()));
    }

    public Mono<User> findUserById(final long userId) {
        return Mono.justOrEmpty(userRepository.findById(userId))
                .switchIfEmpty(Mono.error(new UserEntityNotFoundException("User not found with id.: " + userId)))
                .doOnSuccess(foundUser -> log.debug("Found user with id.: {}", userId));
    }

    public Mono<List<User>> findAllUsers() {
        return Mono.justOrEmpty(userRepository.findAll())
                .flatMap(users -> {
                    final List<User> finds = new LinkedList<>();
                    users.forEach(user -> finds.add(user));
                    return Mono.justOrEmpty(finds);
                })
                .doOnSuccess(users -> log.debug("Found all users"));
    }

    public Mono<User> updateUser(final long userId, final User modifyUser) {
        return Mono.just(userId)
                .flatMap(id -> findUserById(userId))
                .flatMap(foundUser -> Mono.justOrEmpty(userRepository.save(modifyUser)))
                .doOnSuccess(modifiedUser -> log.info("Modified user with id.: {}", modifiedUser.getId()));
    }

    public Mono<Void> deleteUserById(final long userId) {
        return Mono.just(userId)
                .flatMap(id -> findUserById(userId))
                .flatMap(foundUser -> {
                    userRepository.deleteById(foundUser.getId());
                    log.info("Deleted user with id.: {}", userId);
                    return Mono.empty();
                });
    }
}

package com.wsmartsystem.usereditor.user.api;

import com.wsmartsystem.usereditor.user.domain.User;
import com.wsmartsystem.usereditor.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/users")
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> create(@RequestBody final User createUser) {
        return userService.createUser(createUser);
    }

    @GetMapping("/{userId}")
    public Mono<User> findById(@PathVariable final long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping
    public Mono<List<User>> findAll() {
        return userService.findAllUsers();
    }

    @PutMapping("/{userId}")
    public Mono<User> update(@PathVariable final long userId,
                             @RequestBody final User modifyUser) {
        return userService.updateUser(userId, modifyUser);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable final long userId) {
        return userService.deleteUserById(userId);
    }
}

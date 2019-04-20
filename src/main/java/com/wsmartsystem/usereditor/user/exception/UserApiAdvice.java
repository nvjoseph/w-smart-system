package com.wsmartsystem.usereditor.user.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Log4j2
@RestControllerAdvice
public class UserApiAdvice {

    @ExceptionHandler(UserEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<String> handleUserEntityNotFoundException(final UserEntityNotFoundException exception) {
        log.error("handleUserEntityNotFoundException > {}", exception);
        return Mono.just(exception.getLocalizedMessage());
    }
}

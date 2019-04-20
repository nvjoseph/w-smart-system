package com.wsmartsystem.usereditor.user.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserEntityNotFoundException extends RuntimeException {

    public UserEntityNotFoundException(final String message) {
        super(message);
    }
}

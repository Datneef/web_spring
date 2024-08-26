package com.spring.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book not found")
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message, int status) {
        super(message);
        this.massage = message;
        this.status = status;
    }

    public NotFoundException(String message) {
        super(message);
        this.massage = message;
    }

    int status;
    String massage;
}

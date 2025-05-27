package com.example.forum.advice;

import com.example.forum.dto.response.MessageResponse;
import com.example.forum.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    private ResponseEntity<MessageResponse> buildResponse(HttpStatus status, String message, Exception exception) {
        if (status.is5xxServerError()) {
            logger.error(message, exception);
        } else {
            logger.warn(message, exception);
        }
        return new ResponseEntity<>(new MessageResponse(message), status);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<MessageResponse> handleAuthException(AuthException exception, WebRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), exception);
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    public ResponseEntity<MessageResponse> handleEmailNotVerified(EmailNotVerifiedException exception, WebRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, exception.getMessage(), exception);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<MessageResponse> handleUserAlreadyExists(UserAlreadyExistsException exception, WebRequest request) {
        return buildResponse(HttpStatus.CONFLICT, exception.getMessage(), exception);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageResponse> handleUserNotFound(UserNotFoundException exception, WebRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    public ResponseEntity<MessageResponse> handleUserRoleNotFound(UserRoleNotFoundException exception, WebRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResponse> handleAll(Exception exception, WebRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later.",
                exception);
    }
}

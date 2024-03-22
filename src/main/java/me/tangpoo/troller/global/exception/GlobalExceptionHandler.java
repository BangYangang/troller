package me.tangpoo.troller.global.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;
import java.util.Objects;
import javax.security.auth.login.LoginException;
import me.tangpoo.troller.global.exception.custom.EntityNotMatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> validExceptionHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionForm> NoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionForm(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
                e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionForm> AccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionForm(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
                e.getMessage()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionForm> NullPointerException(NullPointerException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionForm(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
                e.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionForm> DataIntegrityViolationException(
        DataIntegrityViolationException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionForm(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
                e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionForm> IllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionForm(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST,
                e.getMessage()));
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ExceptionForm> LoginException(LoginException e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionForm((HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST,
                e.getMessage()));
    }

    @ExceptionHandler({
        EntityNotFoundException.class,
        EntityNotMatchException.class,
        EntityExistsException.class
    })
    public ResponseEntity<ExceptionForm> entityNotFoundExceptionHandler(Exception e) {
        return ResponseEntity.badRequest()
            .body(new ExceptionForm((HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST,
                e.getMessage()));
    }

}

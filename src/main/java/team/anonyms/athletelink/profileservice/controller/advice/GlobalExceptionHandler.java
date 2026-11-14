package team.anonyms.athletelink.profileservice.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import team.anonyms.athletelink.profileservice.exception.EntityAlreadyExistsException;

@Slf4j
@RestControllerAdvice
@SuppressWarnings(value = {"unused"})
public class GlobalExceptionHandler {
    private void makeDefaultErrorLog(Exception e) {
        log.atError()
                .setMessage(e.getMessage())
                .setCause(e.getCause())
                .log();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(exception = EntityNotFoundException.class)
    public void handleEntityNotFoundException(EntityNotFoundException e) {
        makeDefaultErrorLog(e);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(exception = EntityAlreadyExistsException.class)
    public void handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        makeDefaultErrorLog(e);
    }
}

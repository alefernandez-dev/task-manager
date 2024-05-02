package dev.alejandro.taskmanager.common.infrastructure;

import dev.alejandro.taskmanager.common.application.ApplicationException;
import dev.alejandro.taskmanager.common.domain.DomainException;
import dev.alejandro.taskmanager.common.domain.InvalidIdentifierException;
import dev.alejandro.taskmanager.task.application.EmployeesForTaskNotFoundException;
import dev.alejandro.taskmanager.user.application.UserNotFoundException;
import dev.alejandro.taskmanager.user.application.UsernameAlreadyExistsError;
import dev.alejandro.taskmanager.user.domain.InvalidPasswordException;
import dev.alejandro.taskmanager.user.domain.InvalidUserRoleException;
import dev.alejandro.taskmanager.user.domain.InvalidUsernameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException e) {
        logger.error(e.getMessage());
        return switch (e) {
            case InvalidUsernameException _ -> ResponseEntity.badRequest().body(ErrorResponse.single("username value is invalid"));
            case InvalidPasswordException _ -> ResponseEntity.badRequest().body(ErrorResponse.single("password value in invalid"));
            case InvalidUserRoleException _ -> ResponseEntity.badRequest().body(ErrorResponse.single("role value is invalid"));
            case InvalidIdentifierException _ -> ResponseEntity.badRequest().body(ErrorResponse.single("identifier value is invalid"));
            default -> throw new IllegalStateException();
        };
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException e) {
        logger.error(e.getMessage());
        return switch (e) {
            case UserNotFoundException _ -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.single("user not found"));
            case UsernameAlreadyExistsError _ -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.single("username already exist"));
            case EmployeesForTaskNotFoundException _ -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.single("no employees available to perform the requested task"));
            default -> throw new IllegalStateException();
        };
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInfrastructureException(InfrastructureException e) {
        logger.error(e.getMessage());
        return switch (e) {
            case InvalidRequestException i -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.list(i.getErrors()));
            default -> throw new IllegalStateException();
        };
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleFrameworkException(Exception e) {
        logger.error(e.getMessage(), e);
        return switch (e) {
            case HttpRequestMethodNotSupportedException ex -> ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(ErrorResponse.single(ex.getMessage()));
            case NoResourceFoundException ex -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.single(ex.getMessage()));
            default -> ResponseEntity.internalServerError().build();
        };
    }
}

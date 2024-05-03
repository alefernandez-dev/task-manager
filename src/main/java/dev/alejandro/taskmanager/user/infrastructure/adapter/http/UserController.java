package dev.alejandro.taskmanager.user.infrastructure.adapter.http;

import dev.alejandro.taskmanager.user.application.*;
import dev.alejandro.taskmanager.user.infrastructure.UserRequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UserRequestValidator validator;
    private final RetrieveUsersUseCase retrieveUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserByIdUseCase deleteUserByIdUseCase;

    public UserController(CreateUserUseCase createUserUseCase, UserRequestValidator validator, RetrieveUsersUseCase retrieveUsersUseCase, UpdateUserUseCase updateUserUseCase, DeleteUserByIdUseCase deleteUserByIdUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.validator = validator;
        this.retrieveUsersUseCase = retrieveUsersUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserByIdUseCase = deleteUserByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserRequest userRequest) {
        validator.validate(userRequest);
        createUserUseCase.create(UserInput.of(userRequest.username(), userRequest.password(), userRequest.role()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> list(@RequestParam(required = false) String username) {
        List<UserResponse> users = new ArrayList<>();
        if (username != null) {
            users = retrieveUsersUseCase
                    .retrieveByName(username)
                    .stream()
                    .map(this::toResponse)
                    .toList();
        } else {
            users = retrieveUsersUseCase
                    .retrieveAll()
                    .stream().map(this::toResponse)
                    .toList();
        }
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable(name = "id") String userId) {
        var userOutput = retrieveUsersUseCase.retrieveById(userId);
        return ResponseEntity.ok(this.toResponse(userOutput));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserRequest userRequest, @PathVariable(name = "id") String userId) {
        validator.validate(userRequest);
        updateUserUseCase.update(UserInput.of(userRequest.username(), userRequest.password(), userRequest.role()), userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String userId) {
        deleteUserByIdUseCase.delete(userId);
        return ResponseEntity.ok().build();
    }

    private UserResponse toResponse(UserOutput userOutput) {
        return UserResponse.of(userOutput.userId().toString(), userOutput.username(), userOutput.role());
    }

}

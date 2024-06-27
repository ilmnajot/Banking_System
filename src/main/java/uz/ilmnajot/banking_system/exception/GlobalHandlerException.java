package uz.ilmnajot.banking_system.exception;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.banking_system.model.ApiResponse;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
return ResponseEntity
        .badRequest()
        .body(
                ApiResponse
                        .builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .success(false)
                        .errors(errors)
                        .build()
        );
    }

        @ExceptionHandler(IllegalArgumentException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            ApiResponse
                                    .builder()
                                    .status(HttpStatus.BAD_REQUEST)
                                    .success(false)
                                    .errors(ex.getMessage())
                                    .build()
                    );
        }

        @ExceptionHandler(RuntimeException.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        @ResponseBody
        public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(
                            ApiResponse
                                    .builder()
                                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .success(false)
                                    .errors(ex.getMessage())
                                    .build()
                    );
        }

        @ExceptionHandler(EntityNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ResponseBody
        public ResponseEntity<ApiResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse
                            .builder()
                            .status(HttpStatus.NOT_FOUND)
                            .success(false)
                            .errors(ex.getMessage())
                            .build()
                    );
        }

        @ExceptionHandler(EntityExistsException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        @ResponseBody
        public ResponseEntity<ApiResponse> handleEntityExistsException(EntityExistsException ex) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(
                            ApiResponse
                                    .builder()
                                    .status(HttpStatus.CONFLICT)
                                    .success(false)
                                    .errors(ex.getMessage())
                                    .build()
                    );
        }


        // Authentication
        @ExceptionHandler(BadCredentialsException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        @ResponseBody
        public ResponseEntity<ApiResponse> handelBadCredentialsException(BadCredentialsException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(
                            ApiResponse
                                    .builder()
                                    .status(HttpStatus.UNAUTHORIZED)
                                    .success(false)
                                    .errors(ex.getMessage())
                                    .build()
                    );
        }

        @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        @ResponseBody
        public ResponseEntity<ApiResponse> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(
                            ApiResponse
                                    .builder()
                                    .status(HttpStatus.UNAUTHORIZED)
                                    .success(false)
                                    .errors("Authentication credentials not found")
                                    .build()
                    );
        }

        // Transaction
        @ExceptionHandler(LowBalanceException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public ResponseEntity<ApiResponse> handleLowBalanceException(LowBalanceException ex){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse
                            .builder()
                            .status(HttpStatus.BAD_REQUEST)
                            .success(false)
                            .errors(ex.getMessage())
                            .build()
                    );

        }
    }


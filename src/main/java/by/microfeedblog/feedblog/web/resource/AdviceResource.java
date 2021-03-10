package by.microfeedblog.feedblog.web.resource;

import by.microfeedblog.feedblog.service.exception.*;
import by.microfeedblog.feedblog.web.exception.AccessException;
import by.microfeedblog.feedblog.web.exception.LoginException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class AdviceResource extends ResponseEntityExceptionHandler {

    private static final ResponseEntity<?> BAD_REQUEST_RESPONSE = ResponseEntity.badRequest().build();

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, String> message = new LinkedHashMap<>();
        message.put("message", "Validation problems");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            message.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(message, headers, status);
    }

    @ExceptionHandler({PostNotFoundException.class,
            PostTitleException.class,
            CategoryNotFoundException.class,
            CategoryIsAlreadyExistException.class,
            TagIsAlreadyExistException.class,
            TagNotFoundException.class,
            UserNotFoundException.class,
            UserIsAlreadyExistException.class,
            CommentNotFoundException.class,
            AccessException.class,
            LoginException.class})
    public ResponseEntity<?> postNotfound() {
        return BAD_REQUEST_RESPONSE;
    }

}

package com.stackroute.giphermanager.config;

import com.stackroute.giphermanager.exception.GIFAlreadyExistsException;
import com.stackroute.giphermanager.exception.GIFNotFoundException;
import com.stackroute.giphermanager.exception.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GIFNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(GIFNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("GIF Not Found..");
    }

    @ExceptionHandler(GIFAlreadyExistsException.class)
    public ResponseEntity<Object> handelAlreadyExistException(GIFAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("GIF Already Exist...");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handelUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid user name !! Please check username");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("OOPS... something went wrong please try again after some time..");
    }
}

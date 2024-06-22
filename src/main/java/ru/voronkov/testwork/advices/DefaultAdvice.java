package ru.voronkov.testwork.advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.voronkov.testwork.dto.Response;
import ru.voronkov.testwork.exception.BadRequestException;
import ru.voronkov.testwork.exception.NotFoundException;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response> handleCarValidationException(BadRequestException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handlePersonValidationException(NotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
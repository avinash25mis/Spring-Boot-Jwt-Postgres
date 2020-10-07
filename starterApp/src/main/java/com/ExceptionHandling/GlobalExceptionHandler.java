package com.ExceptionHandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Autowired
    private Environment env;




    @ExceptionHandler(AppExceptions.class)
    public final ResponseEntity<Object> scpExceptions(AppExceptions ex, WebRequest request) {
        String errorMessage=ex.getErrorMsg();
        String details=ex.getDetailMessage();
        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse(errorMessage, details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> defaultHandler(Exception ex, WebRequest request) {
        String errorMessage=ex.getLocalizedMessage();
        String details = "";
        if(ex.getCause()!=null && ex.getCause()!=null && ex.getCause().getCause()!=null) {
            details=ex.getCause().getCause().toString();
        }else{
            details=ex.getLocalizedMessage();
        }


        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse(errorMessage, details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
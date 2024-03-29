package com.shah.assignmentschooladminapi.advice;


import com.shah.assignmentschooladminapi.exception.MyException;
import com.shah.assignmentschooladminapi.model.response.AdminResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author NORUL
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * User-defined exception for business related exceptions
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MyException.class})
    @ResponseBody
    public AdminResponse handleAdminException(MyException e) {
        return AdminResponse.failureResponse(e.getErrorMessage());
    }

    /**
     * to handle constraint when validating request body from client input
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public AdminResponse handleMethodArgumentNotValidException(
            HttpServletRequest req, MethodArgumentNotValidException e) {

        List<String> cause = new ArrayList<>();

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            cause.add(error.getDefaultMessage());
        }
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            cause.add(error.getDefaultMessage());
        }
        String errorMessages = String.join(", ", cause);

        log.error("requestUrl : {}, occurred an error : {}", req.getRequestURI(), errorMessages);
        return AdminResponse.failureResponse(errorMessages);
    }

    /**
     * When an action violates a constraint validation
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public AdminResponse handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException e) {

        List<String> errorMessages = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        log.error("requestUrl : {}, occurred an error : {}", req.getRequestURI(), errorMessages);
        String collect = String.join(", ", errorMessages);
        return AdminResponse.failureResponse(collect);
    }

    /**
     * For all other unexpected exceptions
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public AdminResponse handleOtherException(Exception e) {
        log.error("handleOtherException caught here: {}", e.getMessage());
        return AdminResponse.failureResponse(e.getLocalizedMessage());
    }

}

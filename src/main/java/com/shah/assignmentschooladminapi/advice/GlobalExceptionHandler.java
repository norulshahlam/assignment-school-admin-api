package com.shah.assignmentschooladminapi.advice;


import com.shah.assignmentschooladminapi.exception.AdminException;
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
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * User-defined exception for business related exceptions
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({AdminException.class})
    @ResponseBody
    public AdminResponse handleAdminException(AdminException e) {
        return AdminResponse.failureResponse(e.getErrorMessage());
    }

    /**
     * to handle constraint when validating request body from client input
     *
     * @param req
     * @param e
     * @return
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

        log.error("ERROR_CAUSED_BY: ", errorMessages);
        return AdminResponse.failureResponse(errorMessages);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public AdminResponse handleOtherException(Exception e) {
        return AdminResponse.failureResponse(e.getLocalizedMessage());
    }

}

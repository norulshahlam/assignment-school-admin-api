package com.shah.assignmentschooladminapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author NORUL
 */
@Data
@AllArgsConstructor
public class MyException extends RuntimeException {

    private final String errorMessage;
}

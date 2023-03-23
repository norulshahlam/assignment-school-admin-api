package com.shah.assignmentschooladminapi.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author NORUL
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminResponse {

    String message;

    public static AdminResponse failureResponse(String message) {
        return AdminResponse.builder()
                .message(message)
                .build();
    }
}

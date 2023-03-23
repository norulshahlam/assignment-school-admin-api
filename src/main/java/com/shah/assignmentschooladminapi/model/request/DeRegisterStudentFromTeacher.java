package com.shah.assignmentschooladminapi.model.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author NORUL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeRegisterStudentFromTeacher {

    @Schema(type = "string", example = "teacher1@xyz.com")
    @NotBlank(message ="teacher email cannot be blank")
    @Email(message = "teacher email address must be in proper format")
    private String teacher;

    @Schema(type = "string", example = "student1@xyz.com")
    @NotBlank(message ="student email cannot be blank")
    @Email(message = "student email address must be in proper format")
    private String student;

    @Schema(type = "string", example = "Cancelled enrollment")
    @NotBlank(message ="reason cannot be blank")
    private String reason;
}

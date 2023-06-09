package com.shah.assignmentschooladminapi.model.dto;


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
public class TeacherDto {

    @Schema(type = "string", example = "teacher1@xyz.com")
    @NotBlank(message ="teacher email cannot be blank")
    @Email(message = "email address must be in proper format")
    private String email;

    @Schema(type = "string", example = "teacher1")
    @NotBlank(message ="teacher name cannot be blank")
    private String name;
}

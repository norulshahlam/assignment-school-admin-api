package com.shah.assignmentschooladminapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    @Schema(type = "string", example = "abc@xyz.com")
    @NotBlank(message ="student email cannot be blank")
    @Email(message = "email address must be in proper format")
    private String email;

    @Schema(type = "string", example = "abc")
    @NotBlank(message ="student name cannot be blank")
    private String name;
}

package com.shah.assignmentschooladminapi.model.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author NORUL
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterStudents {

    @Schema(type = "string", example = "teacher1@xyz.com")
    @NotBlank(message = "teacher Email cannot be blank")
    @Email(message = "teacher email address must be in proper format")
    private String teacher;

    @Schema(type = "array", example = "[\"student1@xyz.com\"]")
    private List<@Email(message = "student email address must be in proper format")
            @NotEmpty(message = "students Email cannot be blank")
                    String> students;
}

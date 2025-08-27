package org.iclass.spring_9jwt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    @NotBlank
    private String title;
    
    @NotBlank
    private String content;
}

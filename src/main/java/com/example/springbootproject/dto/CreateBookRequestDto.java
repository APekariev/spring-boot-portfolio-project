package com.example.springbootproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookRequestDto {

    @NotBlank
    @Size(min = 2, max = 255, message = "The title shall be from 2 to 255 characters long")
    private String title;
    @NotBlank
    @Size(min = 2, max = 255, message = "The author name shall be from 2 to 255 characters long")
    private String author;
    @NotNull
    @Size(min = 4, max = 255, message = "isbn length shall always be from 4 to 255 characters")
    @Pattern(regexp = "^[0-9]+(-[0-9]+)*$", message = "isbn shall always contain "
            + "digits or hyphens only with digits at the beginning and the end")
    private String isbn;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @Size(max = 255)
    private String description;
    @Size(max = 255)
    private String coverImage;
}


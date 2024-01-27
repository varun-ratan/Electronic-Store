package com.electronic.store.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDto {

    private String categoryId;
    @NotBlank
    @Min(value=4, message="title must be of minimum 4 characters !!")
    private String title;
    @NotBlank(message="Description required !!")
    private String description;

    private String coverImage;
}

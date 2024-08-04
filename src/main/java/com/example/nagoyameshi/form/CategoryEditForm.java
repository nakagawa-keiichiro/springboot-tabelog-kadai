package com.example.nagoyameshi.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryEditForm {
    private Integer categoryId;  
    @NotBlank(message = "カテゴリ名を入力してください。")
    private String categoryName;
}

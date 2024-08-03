package com.example.nagoyameshi.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPasswordEditForm {

    private Integer id;
    
    @NotBlank(message = "パスワードを入力してください。")
    private String password;
    
    @NotBlank(message = "新しいパスワードを入力してください。")
    private String newPassword;
}

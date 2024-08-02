package com.example.nagoyameshi.form;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreEditForm {
    @NotNull
    private Integer storeId;  
    
    @NotNull(message = "カテゴリを選択してください。")
    private Integer categoryId;  
    
    @NotBlank(message = "店舗名を入力してください。")
    private String storeName;
        
    private MultipartFile imageFile;
    
    @NotBlank(message = "説明を入力してください。")
    private String remarks;   
    
    @NotNull(message = "価格帯（下限）を入力してください。")
    @Min(value = 1, message = "料金は1円以上に設定してください。")
    private Integer priceLowerLimit; 
    
    @NotNull(message = "価格帯（上限）を入力してください。")
    @Min(value = 1, message = "料金は1円以上に設定してください。")
    private Integer priceUpperLimit;  
    
    @NotNull(message = "開店時間を入力してください。")
    private Integer businessHoursOpen;   
    
    @NotNull(message = "閉店時間を入力してください。")
    private Integer businessHoursClose;  
    
    @NotBlank(message = "定休日を入力してください。")
    private String regularHoliday;  
    
    @NotBlank(message = "郵便番号を入力してください。")
    private String postalCode;
    
    @NotBlank(message = "住所を入力してください。")
    private String address;
    
    @NotBlank(message = "電話番号を入力してください。")
    private String phoneNumber;
}

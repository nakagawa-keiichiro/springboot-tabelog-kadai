package com.example.nagoyameshi.form;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
    private Integer storeId;
    
    private Integer userId;    
        
    private String reservationDate;       
    
    private Integer numberOfPeople;
    
    private String add;
    
    private Integer businessHours;
}

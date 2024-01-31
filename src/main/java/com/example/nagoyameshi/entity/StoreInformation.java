package com.example.nagoyameshi.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "store_information")
@Data
public class StoreInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer storeId;
    
    @Column(name = "category_id")
    private Integer categoryId;
    
    @Column(name = "store_name")
    private String storeName;
    
    @Column(name = "image_name")
    private String imageName;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "price_lower_limit")
    private Integer priceLowerLimit;
    
    @Column(name = "price_upper_limit")
    private Integer priceUpperLimit;
    
    @Column(name = "business_hours_open")
    private String businessHoursOpen;
    
    @Column(name = "business_hours_close")
    private String businessHoursClose;
    
    @Column(name = "postal_code")
    private String postalCode;
    
    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "regular_holiday")
    private String regularHoliday;
 
    @Column(name = "registration_at", insertable = false, updatable = false)
    private Timestamp registrationAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;
}

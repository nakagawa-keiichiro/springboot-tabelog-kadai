package com.example.nagoyameshi.entity;
import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreInformation store; 
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;     
    
    @Column(name = "reservation_date")
    private LocalDate reservationDate;  
    
    @Column(name = "number_of_people")
    private Integer numberOfPeople;    
    
    @Column(name = "business_hours")
    private Integer businessHours;  
    
    @Column(name = "registration_at", insertable = false, updatable = false)
    private Timestamp registrationAt;
    
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;  
}

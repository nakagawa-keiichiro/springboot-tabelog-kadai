package com.example.nagoyameshi.entity;

import java.sql.Timestamp;

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
@Table(name = "reviews")
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
    
    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreInformation storeId;    
        
    @Column(name = "stars")
    private Integer stars;
        
    @Column(name = "comment")
    private String comment;    
    
    @Column(name = "registration_at", insertable = false, updatable = false)
    private Timestamp registrationAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;
}

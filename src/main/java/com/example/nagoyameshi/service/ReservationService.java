package com.example.nagoyameshi.service;
import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReservationRegisterForm;
import com.example.nagoyameshi.repository.ReservationRepository;
import com.example.nagoyameshi.repository.StoreInformationRepository;
import com.example.nagoyameshi.repository.UserRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;  
    private final StoreInformationRepository storeInformationRepository;  
    private final UserRepository userRepository;  
    
    public ReservationService(ReservationRepository reservationRepository, StoreInformationRepository storeInformationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;  
        this.storeInformationRepository = storeInformationRepository;  
        this.userRepository = userRepository;  
    }    
    
    @Transactional
    public void create(ReservationRegisterForm reservationRegisterForm) { 
        Reservation reservation = new Reservation();
        StoreInformation storeInformation = storeInformationRepository.getReferenceById(reservationRegisterForm.getStoreId());
        User user = userRepository.getReferenceById(reservationRegisterForm.getUserId());
        LocalDate reservationDate = LocalDate.parse(reservationRegisterForm.getReservationDate());        
                
        reservation.setStore(storeInformation);
        reservation.setUser(user);
        reservation.setReservationDate(reservationDate);
        reservation.setNumberOfPeople(reservationRegisterForm.getNumberOfPeople());
        
        reservationRepository.save(reservation);
    } 
}

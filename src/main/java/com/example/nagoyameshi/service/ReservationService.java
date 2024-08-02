package com.example.nagoyameshi.service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReservationInputForm;
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
        reservation.setBusinessHours(reservationRegisterForm.getBusinessHours());
        
        reservationRepository.save(reservation);
    } 
    
    public String checkBusinessHours(StoreInformation storeInformation, ReservationInputForm reservationInputForm, LocalDate reservationDate) { 
    	
    	// 年月日
        //int year = reservationDate.getYear();
        //int month = reservationDate.getMonthValue();
        //int day = reservationDate.getDayOfMonth();
        
    	// 予約時間
    	int businessHours = reservationInputForm.getBusinessHours();
    	// 開店時間
    	int businessHoursOpen = storeInformation.getBusinessHoursOpen();
    	// 閉店時間
    	int businessHoursClose = storeInformation.getBusinessHoursClose();
    	
        // 現在日時を特定のタイムゾーンで取得
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");  // ローカルタイムゾーンに合わせて設定
        ZonedDateTime currentZonedDateTime = ZonedDateTime.now(zoneId);
        
        // 予約日及び営業時間を同じタイムゾーンに設定
        ZonedDateTime businessHoursDate = ZonedDateTime.of(
                reservationDate.getYear(), reservationDate.getMonthValue(), reservationDate.getDayOfMonth(),
                businessHours, 0, 0, 0, zoneId);
        
        ZonedDateTime businessHoursOpenDate = ZonedDateTime.of(
                reservationDate.getYear(), reservationDate.getMonthValue(), reservationDate.getDayOfMonth(),
                businessHoursOpen, 0, 0, 0, zoneId);
        
        ZonedDateTime businessHoursCloseDate = ZonedDateTime.of(
                reservationDate.getYear(), reservationDate.getMonthValue(), reservationDate.getDayOfMonth(),
                businessHoursClose, 0, 0, 0, zoneId);
        
        ZonedDateTime currentDate = ZonedDateTime.of(
                currentZonedDateTime.getYear(), currentZonedDateTime.getMonthValue(), currentZonedDateTime.getDayOfMonth(), 
                currentZonedDateTime.getHour(), currentZonedDateTime.getMinute(), 0, 0, zoneId);
        
       // LocalDateTime businessHoursDate = LocalDateTime.of(year, month, day, businessHours, 0, 0);
       // LocalDateTime businessHoursOpenDate = LocalDateTime.of(year, month, day, businessHoursOpen, 0, 0);   
       // LocalDateTime businessHoursCloseDate = LocalDateTime.of(year, month, day, businessHoursClose, 0, 0);
       // LocalDateTime currentDate = LocalDateTime.now();
       // currentDate = LocalDateTime.of(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth(), currentDate.getHour(), currentDate.getMinute(), 0);
        
    	String errorMessage = "";
    	int count = 0;
    	
    	if(currentDate.isAfter(businessHoursDate)){
    		
    		errorMessage = "予約時間を過ぎています。";
    		
    		return errorMessage;
    	}
    	
    	if(businessHoursOpenDate.isAfter(businessHoursCloseDate)){
    		
    		businessHoursCloseDate = businessHoursCloseDate.plusDays(1);
    		count = 1;
    	}
    	
        if(businessHoursOpenDate.isBefore(businessHoursDate) && businessHoursCloseDate.isAfter(businessHoursDate)){
        	
        	errorMessage = "";
        	
        } else if(businessHoursOpenDate.isEqual(businessHoursDate) || businessHoursCloseDate.isEqual(businessHoursDate)){
        	
        	errorMessage = "";
        	
        } else {
        	
        	errorMessage = "営業時間外になります。";
        	
        }
        
        if(count == 1 && !errorMessage.isEmpty()) {
        	
        	businessHoursDate = businessHoursDate.plusDays(1);
        	
        	if(businessHoursOpenDate.isAfter(businessHoursCloseDate)){
        		
        		businessHoursCloseDate = businessHoursCloseDate.plusDays(1);
        		count = 1;
        	}
        	
            if(businessHoursOpenDate.isBefore(businessHoursDate) && businessHoursCloseDate.isAfter(businessHoursDate)){
            	
            	errorMessage = "";
            	
            } else if(businessHoursOpenDate.isEqual(businessHoursDate) || businessHoursCloseDate.isEqual(businessHoursDate)){
            	
            	errorMessage = "";
            	
            } else {
            	
            	errorMessage = "営業時間外になります。";
            	
            }
        }
        
    	return errorMessage;
    } 
}

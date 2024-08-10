package com.example.nagoyameshi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.form.StoreEditForm;
import com.example.nagoyameshi.form.StoreRegisterForm;
import com.example.nagoyameshi.repository.StoreInformationRepository;

@Service
public class StoreService {
	
    private final StoreInformationRepository storeInformationRepository;    
    
    public StoreService(StoreInformationRepository storeInformationRepository) {
        this.storeInformationRepository = storeInformationRepository;        
    }    
    
    @Transactional
    public void create(StoreRegisterForm storeRegisterForm) {
    	StoreInformation store = new StoreInformation();        
        MultipartFile imageFile = storeRegisterForm.getImageFile();
        
        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename(); 
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            store.setImageName(hashedImageName);
        }
        
        store.setStoreName(storeRegisterForm.getStoreName());  
        store.setCategoryId(storeRegisterForm.getCategoryId());  
        store.setRemarks(storeRegisterForm.getRemarks());  
        store.setPriceLowerLimit(storeRegisterForm.getPriceLowerLimit());  
        store.setPriceUpperLimit(storeRegisterForm.getPriceUpperLimit());  
        store.setBusinessHoursOpen(storeRegisterForm.getBusinessHoursOpen());                
        store.setBusinessHoursClose(storeRegisterForm.getBusinessHoursClose());
        store.setPostalCode(storeRegisterForm.getPostalCode());
        store.setAddress(storeRegisterForm.getAddress());
        store.setPhoneNumber(storeRegisterForm.getPhoneNumber());
        store.setRegularHoliday(storeRegisterForm.getRegularHoliday());
                    
        storeInformationRepository.save(store);
    }  
    
    @Transactional
    public void update(StoreEditForm storeEditForm, Integer categoryId, Integer businessHoursOpen, Integer businessHoursClose) {
    	StoreInformation store = storeInformationRepository.getReferenceById(storeEditForm.getStoreId());
        MultipartFile imageFile = storeEditForm.getImageFile();
        
        if (!imageFile.isEmpty()) {
            String imageName = imageFile.getOriginalFilename(); 
            String hashedImageName = generateNewFileName(imageName);
            Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
            copyImageFile(imageFile, filePath);
            store.setImageName(hashedImageName);
        }
        
        store.setStoreName(storeEditForm.getStoreName());  
        store.setCategoryId(categoryId);  
        store.setRemarks(storeEditForm.getRemarks());  
        store.setPriceLowerLimit(storeEditForm.getPriceLowerLimit());  
        store.setPriceUpperLimit(storeEditForm.getPriceUpperLimit());  
        //store.setBusinessHoursOpen(storeEditForm.getBusinessHoursOpen());                
        //store.setBusinessHoursClose(storeEditForm.getBusinessHoursClose());
        store.setBusinessHoursOpen(businessHoursOpen);                
        store.setBusinessHoursClose(businessHoursClose);
        store.setPostalCode(storeEditForm.getPostalCode());
        store.setAddress(storeEditForm.getAddress());
        store.setPhoneNumber(storeEditForm.getPhoneNumber());
        store.setRegularHoliday(storeEditForm.getRegularHoliday());
                    
        storeInformationRepository.save(store);
    }  
    
    // UUIDを使って生成したファイル名を返す
    public String generateNewFileName(String fileName) {
        String[] fileNames = fileName.split("\\.");                
        for (int i = 0; i < fileNames.length - 1; i++) {
            fileNames[i] = UUID.randomUUID().toString();            
        }
        String hashedFileName = String.join(".", fileNames);
        return hashedFileName;
    }     
    
    // 画像ファイルを指定したファイルにコピーする
    public void copyImageFile(MultipartFile imageFile, Path filePath) {           
        try {
            Files.copy(imageFile.getInputStream(), filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }          
    } 
}


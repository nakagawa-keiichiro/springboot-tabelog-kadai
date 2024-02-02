package com.example.nagoyameshi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.repository.StoreInformationRepository;

@Controller
public class HomeStoreListController {
	@Autowired
    private StoreInformationRepository storeInformationRepository;	
    
    @GetMapping("/store")
    public String index(Model model, @PageableDefault(page = 0, size = 3, sort = "storeId", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
        
    	Page<StoreInformation> storeInformationPage;
    	
    	if (keyword != null && !keyword.isEmpty()) {
    		storeInformationPage= storeInformationRepository.findByStoreNameLike("%" + keyword + "%", pageable);
    	}else {
    		storeInformationPage = storeInformationRepository.findAll(pageable);
    	}
    	
        model.addAttribute("storeInformationPage", storeInformationPage); 
        model.addAttribute("keyword", keyword);
        
        return "/index";
    }  
}

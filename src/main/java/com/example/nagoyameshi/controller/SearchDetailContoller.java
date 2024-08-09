package com.example.nagoyameshi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReservationInputForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.FavoriteRepository;
import com.example.nagoyameshi.repository.StoreInformationRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.FavoriteService;

@RequestMapping("/searchDetail")
@Controller
public class SearchDetailContoller {
	@Autowired
    private StoreInformationRepository storeInformationRepository;
	
	@Autowired
    private final CategoryRepository categoryRepository;
	
	@Autowired
	private final FavoriteService favoriteService;
	
	@Autowired
	private final FavoriteRepository favoriteRepository;
	
    public SearchDetailContoller(StoreInformationRepository storeInformationRepository, CategoryRepository categoryRepository, FavoriteRepository favoriteRepository, FavoriteService favoriteService) {
        this.storeInformationRepository = storeInformationRepository;    
        this.categoryRepository = categoryRepository;
		this.favoriteRepository = favoriteRepository;
		this.favoriteService = favoriteService;
    }	
  
    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword,
                        @RequestParam(name = "area", required = false) String area,
                        @RequestParam(name = "price", required = false) Integer price,  
                        @RequestParam(name = "category", required = false) Integer category,
                        @RequestParam(name = "order", required = false) String order,
                        @PageableDefault(page = 0, size = 3, sort = "storeId", direction = Direction.ASC) Pageable pageable,
                        Model model) 
    {
        Page<StoreInformation> storeInformationPage;
    	List<Category> categorys = categoryRepository.findAll();
                
        if (keyword != null && !keyword.isEmpty()) {
            if (order != null && order.equals("priceAsc")) {
            	storeInformationPage = storeInformationRepository.findByStoreNameLikeOrAddressLikeOrderByPriceLowerLimitAsc("%" + keyword + "%", "%" + keyword + "%", pageable);
            } else {
            	storeInformationPage = storeInformationRepository.findByStoreNameLikeOrAddressLikeOrderByRegistrationAtDesc("%" + keyword + "%", "%" + keyword + "%", pageable);
            }         
        } else if (area != null && !area.isEmpty()) {
            if (order != null && order.equals("priceAsc")) {
            	storeInformationPage = storeInformationRepository.findByAddressLikeOrderByPriceLowerLimitAsc("%" + area + "%", pageable);	
            } else {
            	storeInformationPage = storeInformationRepository.findByAddressLikeOrderByRegistrationAtDesc("%" + area + "%", pageable);
            }  
        } else if (price != null) {
            if (order != null && order.equals("priceAsc")) {
            	storeInformationPage = storeInformationRepository.findByPriceLowerLimitLessThanEqualOrderByPriceLowerLimitAsc(price, pageable);
            } else {
            	storeInformationPage = storeInformationRepository.findByPriceLowerLimitLessThanEqualOrderByRegistrationAtDesc(price, pageable);
            }  
        } else if(category != null) {
        	if (order != null && order.equals("priceAsc")) {
        		storeInformationPage = storeInformationRepository.findByCategoryIdOrderByPriceLowerLimitAsc(category,pageable);
            } else {
            	storeInformationPage = storeInformationRepository.findByCategoryIdOrderByRegistrationAtDesc(category,pageable);
            } 
        	//storeInformationPage = storeInformationRepository.findByCategoryId(category, pageable);
        } else {
            if (order != null && order.equals("priceAsc")) {
            	storeInformationPage = storeInformationRepository.findAllByOrderByPriceLowerLimitAsc(pageable);
            } else {
            	storeInformationPage = storeInformationRepository.findAllByOrderByRegistrationAtDesc(pageable);   
            } 
        }
        
        model.addAttribute("storeInformationPage", storeInformationPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("area", area);
        model.addAttribute("price", price);   
        model.addAttribute("category", category);
        model.addAttribute("order", order);
        model.addAttribute("categorys", categorys);
        
        return "searchDetail/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id,@RequestParam("add") String topicPath, Model model, @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
    	StoreInformation storeInformation = storeInformationRepository.findByStoreId(id);
    	
        Favorite favorite = null;
        boolean isFavorite = false;
        if (userDetailsImpl != null) {
        	User user = userDetailsImpl.getUser();
        	isFavorite = favoriteService.isFavorite(storeInformation, user);
        	if (isFavorite) {
        		favorite = favoriteRepository.findByStoreAndUser(storeInformation, user);
        	}
        	
        }
        
        model.addAttribute("storeInformation", storeInformation);  
        model.addAttribute("topicPath", topicPath);
        model.addAttribute("reservationInputForm", new ReservationInputForm());
        model.addAttribute("favorite", favorite);
        model.addAttribute("isFavorite", isFavorite);
        
        return "searchDetail/show";
    }    
}

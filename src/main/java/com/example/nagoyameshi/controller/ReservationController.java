package com.example.nagoyameshi.controller;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Reservation;
import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReservationInputForm;
import com.example.nagoyameshi.form.ReservationRegisterForm;
import com.example.nagoyameshi.repository.ReservationRepository;
import com.example.nagoyameshi.repository.StoreInformationRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.ReservationService;

@RequestMapping("")
@Controller
public class ReservationController {
    private final ReservationRepository reservationRepository;    
    private final StoreInformationRepository storeInformationRepository;
    private final ReservationService reservationService;
    
    
    public ReservationController(ReservationRepository reservationRepository, StoreInformationRepository storeInformationRepository, ReservationService reservationService) {        
        this.reservationRepository = reservationRepository;  
        this.storeInformationRepository = storeInformationRepository;  
        this.reservationService = reservationService;
    }    

    @GetMapping("/reservations")
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
        User user = userDetailsImpl.getUser();
        Page<Reservation> reservationPage = reservationRepository.findByUserOrderByRegistrationAtDesc(user, pageable);
        
        model.addAttribute("reservationPage", reservationPage);         
        
        return "reservations/index";
    }
    
    @GetMapping("/searchDetail/{id}/reservations/input")
    public String input(@PathVariable(name = "id") Integer id,
                        @ModelAttribute @Validated ReservationInputForm reservationInputForm,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes, @RequestParam(name = "topicPath", required = false) String topicPath,
                        Model model)
    {   
    	StoreInformation storeInformation = storeInformationRepository.getReferenceById(id);    
    	reservationInputForm.setAdd(topicPath);
        
        //予約日を取得する
        LocalDate reservationDate = reservationInputForm.getCheckinDate();
        
        // 営業時間のチェック
        String errorMessage = reservationService.checkBusinessHours(storeInformation, reservationInputForm, reservationDate);
        if (!errorMessage.isEmpty()) {
            model.addAttribute("storeInformation", storeInformation); 
            model.addAttribute("errorMessage", errorMessage); 
            return "searchDetail/show";
        }

        if (bindingResult.hasErrors()) {            
            model.addAttribute("storeInformation", storeInformation);            
            model.addAttribute("errorMessage", "予約内容に不備があります。"); 
            return "searchDetail/show";
        }
        
        redirectAttributes.addFlashAttribute("reservationInputForm", reservationInputForm);  
        
        return "redirect:/searchDetail/{id}/reservations/confirm";
    } 
    
    @GetMapping("/searchDetail/{id}/reservations/confirm")
    public String confirm(@PathVariable(name = "id") Integer id,
                          @ModelAttribute ReservationInputForm reservationInputForm,
                          @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,                          
                          Model model) 
    {        
    	StoreInformation storeInformation = storeInformationRepository.getReferenceById(id);
        User user = userDetailsImpl.getUser(); 
                
        //予約日を取得する
        LocalDate reservationDate = reservationInputForm.getCheckinDate();
        
        ReservationRegisterForm reservationRegisterForm = new ReservationRegisterForm(storeInformation.getStoreId(), user.getId(), reservationDate.toString(), reservationInputForm.getNumberOfPeople(), reservationInputForm.getAdd(), reservationInputForm.getBusinessHours());
        
        model.addAttribute("storeInformation", storeInformation);  
        model.addAttribute("reservationRegisterForm", reservationRegisterForm);       
        
        return "reservations/confirm";
    }  
    
    @PostMapping("/searchDetail/{id}/reservations/create")
    public String create(@ModelAttribute ReservationRegisterForm reservationRegisterForm) {                
        reservationService.create(reservationRegisterForm);        
        
        return "redirect:/reservations?reserved";
    }
    
    @PostMapping("/reservations/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {        
    	
    	reservationRepository.deleteById(id);
                
        redirectAttributes.addFlashAttribute("successMessage", "予約をキャンセルしました。");
        
        return "redirect:/reservations";
    }  
}

package com.example.nagoyameshi.controller;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.UserEditForm;
import com.example.nagoyameshi.form.UserPasswordEditForm;
import com.example.nagoyameshi.repository.UserRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
    private final UserRepository userRepository;    
    private final UserService userService; 
    
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;    
        this.userService = userService;
    }    
    
    @GetMapping
    public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {         
        User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());  
        
        model.addAttribute("user", user);
        
        return "user/index";
    }
    
    @GetMapping("/edit")
    public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {        
        User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());  
        UserEditForm userEditForm = new UserEditForm(user.getId(), user.getName(), user.getFurigana(), user.getPostalCode(), user.getAddress(), user.getPhoneNumber(), user.getEmail(), user.getRole().getId());
        
        model.addAttribute("userEditForm", userEditForm);
        
        return "user/edit";
    }  
    
    @PostMapping("/update")
    public String update(@ModelAttribute @Validated UserEditForm userEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // メールアドレスが変更されており、かつ登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
        if (userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
            bindingResult.addError(fieldError);                       
        }
        
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        
        userService.update(userEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");
        
        return "redirect:/user";
    } 
    
    @GetMapping("/password")
    public String password(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {        
    	User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId()); 
        UserPasswordEditForm userPasswordEditForm = new UserPasswordEditForm(user.getId(), user.getPassword(), "");
        
        model.addAttribute("userPasswordEditForm", userPasswordEditForm);
        
        return "user/password";
    } 
    
    @PostMapping("/passwordUpdate")
    public String passwordUpdate(@ModelAttribute @Validated UserPasswordEditForm userPasswordEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        
    	if (bindingResult.hasErrors()) {
            return "user/password";
        }
        
    	User user = userRepository.getReferenceById(userPasswordEditForm.getId());  

        if (!userService.isPassword(user, userPasswordEditForm)) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "password", "パスワードが違います。");
            bindingResult.addError(fieldError);                       
        }
        
        if (userService.isPasswordChanged(user, userPasswordEditForm)) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "newPassword", "すでに登録済みのパスワードです。");
            bindingResult.addError(fieldError);                       
        }
        
    	if (bindingResult.hasErrors()) {
            return "user/password";
        }
        
        userService.passwordUpdate(user, userPasswordEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "パスワードを変更しました。");
        redirectAttributes.addAttribute("id", userPasswordEditForm.getId());
        
        return "redirect:/user";
    } 
}

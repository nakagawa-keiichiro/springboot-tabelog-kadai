package com.example.nagoyameshi.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.AdminUserEditForm;
import com.example.nagoyameshi.repository.UserRepository;
import com.example.nagoyameshi.service.AdminUserService;

@RequestMapping("/admin/users")
@Controller
public class AdminUserController {
    private final UserRepository userRepository;
    private final AdminUserService adminUserService;
    
    public AdminUserController(UserRepository userRepository, AdminUserService adminUserService) {
        this.userRepository = userRepository; 
        this.adminUserService = adminUserService;
    }    
    
    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable, Model model) {
        Page<User> userPage;
        
        if (keyword != null && !keyword.isEmpty()) {
            userPage = userRepository.findByNameLikeOrFuriganaLike("%" + keyword + "%", "%" + keyword + "%", pageable);                   
        } else {
            userPage = userRepository.findAll(pageable);
        }        
        
        model.addAttribute("userPage", userPage);        
        model.addAttribute("keyword", keyword);                
        
        return "admin/users/index";
    }
    
    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        User user = userRepository.getReferenceById(id);
        
        model.addAttribute("user", user);
        
        return "admin/users/show";
    }   
    
    @GetMapping("/edit{id}")
    public String edit(@PathVariable(name = "id")Integer id , Model model) {        
        User user = userRepository.getReferenceById(id);  
        AdminUserEditForm adminUserEditForm = new AdminUserEditForm(user.getId(), user.getName(), user.getFurigana(), user.getPostalCode(), user.getAddress(), user.getPhoneNumber(), user.getEmail(), user.getRole().getId());
        
        model.addAttribute("adminUserEditForm", adminUserEditForm);
        
        return "admin/users/edit";
    } 
    
    @PostMapping("/update")
    public String update(@ModelAttribute @Validated AdminUserEditForm adminUserEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // メールアドレスが変更されており、かつ登録済みであれば、BindingResultオブジェクトにエラー内容を追加
        if (adminUserService.isEmailChanged(adminUserEditForm) && adminUserService.isEmailRegistered(adminUserEditForm.getEmail())) {
            FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
            bindingResult.addError(fieldError);                       
        }
        
        if (bindingResult.hasErrors()) {
            return "admin/user/edit";
        }
        
        adminUserService.update(adminUserEditForm);
        redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");
        redirectAttributes.addAttribute("id", adminUserEditForm.getId());
        
        return "redirect:/admin/users/{id}";
    }    
}

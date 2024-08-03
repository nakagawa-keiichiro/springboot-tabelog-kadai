package com.example.nagoyameshi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Role;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.AdminUserEditForm;
import com.example.nagoyameshi.form.UserPasswordEditForm;
import com.example.nagoyameshi.repository.RoleRepository;
import com.example.nagoyameshi.repository.UserRepository;

@Service
public class AdminUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public AdminUserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;    
        this.passwordEncoder = passwordEncoder;
    }    
    
    @Transactional
    public void update(AdminUserEditForm adminUserEditForm) {
        User user = userRepository.getReferenceById(adminUserEditForm.getId());
        Role role = roleRepository.getReferenceById(adminUserEditForm.getRoleId());
        
        user.setName(adminUserEditForm.getName());
        user.setFurigana(adminUserEditForm.getFurigana());
        user.setPostalCode(adminUserEditForm.getPostalCode());
        user.setAddress(adminUserEditForm.getAddress());
        user.setPhoneNumber(adminUserEditForm.getPhoneNumber());
        user.setEmail(adminUserEditForm.getEmail()); 
        user.setRole(role);
        
        userRepository.save(user);
    } 
    
    // メールアドレスが登録済みかどうかをチェックする
    public boolean isEmailRegistered(String email) {
        User user = userRepository.findByEmail(email);  
        return user != null;
    }   
    
    // パスワードとパスワード（確認用）の入力値が一致するかどうかをチェックする
    public boolean isSamePassword(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }  
    
    // ユーザーを有効にする
    @Transactional
    public void enableUser(User user) {
        user.setEnabled(true); 
        userRepository.save(user);
    }    
    
    // メールアドレスが変更されたかどうかをチェックする
    public boolean isEmailChanged(AdminUserEditForm adminUserEditForm) {
        User currentUser = userRepository.getReferenceById(adminUserEditForm.getId());
        return !adminUserEditForm.getEmail().equals(currentUser.getEmail());      
    } 
    
    // パスワードの入力値が一致するかどうかをチェックする
    public boolean isPassword(User user, UserPasswordEditForm adminUserEditForm) {
        return passwordEncoder.matches(adminUserEditForm.getPassword(), user.getPassword());
    }  
    
    // パスワードが変更されているかどうかをチェックする
    public boolean isPasswordChanged(User user, UserPasswordEditForm adminUserEditForm) {
    	return passwordEncoder.matches(adminUserEditForm.getNewPassword(), user.getPassword());
    }  
       
        		
    
    @Transactional
    public void passwordUpdate(User user, UserPasswordEditForm adminUserEditForm) {
        
        user.setPassword(passwordEncoder.encode(adminUserEditForm.getNewPassword()));
        
        userRepository.save(user);
    } 
    
}

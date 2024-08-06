package com.example.nagoyameshi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.Role;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.SignupForm;
import com.example.nagoyameshi.form.UserEditForm;
import com.example.nagoyameshi.form.UserPasswordEditForm;
import com.example.nagoyameshi.repository.RoleRepository;
import com.example.nagoyameshi.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;        
        this.passwordEncoder = passwordEncoder;
    }    
    
    @Transactional
    public User create(SignupForm signupForm, Integer general) {
        User user = new User();
        Role role = roleRepository.getReferenceById(general);
        
        user.setName(signupForm.getName());
        user.setFurigana(signupForm.getFurigana());
        user.setPostalCode(signupForm.getPostalCode());
        user.setAddress(signupForm.getAddress());
        user.setPhoneNumber(signupForm.getPhoneNumber());
        user.setEmail(signupForm.getEmail());
        user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        user.setRole(role);
        user.setEnabled(true);        
        
        return userRepository.save(user);
    } 
    
    @Transactional
    public void update(UserEditForm userEditForm) {
        User user = userRepository.getReferenceById(userEditForm.getId());
        Role role = roleRepository.getReferenceById(userEditForm.getRoleId());
        
        user.setName(userEditForm.getName());
        user.setFurigana(userEditForm.getFurigana());
        user.setPostalCode(userEditForm.getPostalCode());
        user.setAddress(userEditForm.getAddress());
        user.setPhoneNumber(userEditForm.getPhoneNumber());
        user.setEmail(userEditForm.getEmail()); 
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
    public boolean isEmailChanged(UserEditForm userEditForm) {
        User currentUser = userRepository.getReferenceById(userEditForm.getId());
        return !userEditForm.getEmail().equals(currentUser.getEmail());      
    }  
    
    // パスワードの入力値が一致するかどうかをチェックする
    public boolean isPassword(User user, UserPasswordEditForm userPasswordEditForm) {
        return passwordEncoder.matches(userPasswordEditForm.getPassword(), user.getPassword());
    }  
    
    // パスワードが変更されているかどうかをチェックする
    public boolean isPasswordChanged(User user, UserPasswordEditForm userPasswordEditForm) {
    	return passwordEncoder.matches(userPasswordEditForm.getNewPassword(), user.getPassword());
    }  
       
        		
    @Transactional
    public void passwordUpdate(User user, UserPasswordEditForm userPasswordEditForm) {
        
        user.setPassword(passwordEncoder.encode(userPasswordEditForm.getNewPassword()));
        
        userRepository.save(user);
    } 
    @Transactional
    public void updateRole(Map<String, String> paymentIntentObject) {
        String userId = paymentIntentObject.get("userId");

        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("指定されたユーザーが見つかりません。"));

        String roleName = paymentIntentObject.get("roleName");

        Role role = roleRepository.findByName(roleName);
        user.setRole(role);

        // ユーザーを保存
        userRepository.save(user);

        // ロールが変更されたので、セッションを無効化して再ログインさせる
        refreshAuthenticationByRole(roleName);
    }
    public void refreshAuthenticationByRole(String newRole) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(newRole));
        Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}

package com.example.nagoyameshi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.form.StoreEditForm;
import com.example.nagoyameshi.form.StoreRegisterForm;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.StoreInformationRepository;
import com.example.nagoyameshi.service.StoreService;

import jakarta.validation.Valid;

@RequestMapping("/admin/stores")
@Controller
public class AdminStoreController {
	
	@Autowired
    private final StoreInformationRepository storeInformationRepository;  
	@Autowired
    private final CategoryRepository categoryRepository;
	@Autowired
	private final StoreService storeService;
	
    
    public AdminStoreController(StoreInformationRepository storeInformationRepository, CategoryRepository categoryRepository, StoreService storeService) {
        this.storeInformationRepository = storeInformationRepository;    
        this.categoryRepository = categoryRepository;
        this.storeService = storeService;
    }	
    
    @GetMapping
    public String index(Model model, @PageableDefault(page = 0, size = 3, sort = "storeId", direction = Direction.ASC) Pageable pageable, @RequestParam(name = "keyword", required = false) String keyword) {
        Page<StoreInformation> storePage;
        
        if (keyword != null && !keyword.isEmpty()) {
        	storePage = storeInformationRepository.findByStoreNameLike("%" + keyword + "%", pageable);                
        } else {
        	storePage = storeInformationRepository.findAll(pageable);
        }  
        
        model.addAttribute("storePage", storePage); 
        model.addAttribute("keyword", keyword);
        
        return "admin/stores/index";
    }  
    
    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
    	StoreInformation store = storeInformationRepository.getReferenceById(id);
        
        model.addAttribute("store", store);
        
        return "admin/stores/show";
    }  
    
    @GetMapping("/register")
    public String register(Model model) {
    	List<Category> category;
    	category = categoryRepository.findAll();
        model.addAttribute("storeRegisterForm", new StoreRegisterForm());
        model.addAttribute("category", category);
        return "admin/stores/register";
    } 
    
    @PostMapping("/create")
    public String create(@ModelAttribute @Valid StoreRegisterForm storeRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {        
        
    	MultipartFile imageFile = storeRegisterForm.getImageFile();
        if (imageFile.isEmpty()) {
            // imageFileが空の場合、BindingResultにエラーを追加
            bindingResult.rejectValue("imageFile", "error.imageFile", "画像ファイルを選択してください。");
        }
    	if (bindingResult.hasErrors()) {
        	List<Category> category;
        	category = categoryRepository.findAll();
        	model.addAttribute("category", category);
            return "admin/stores/register";
        }
        
        storeService.create(storeRegisterForm);
        redirectAttributes.addFlashAttribute("successMessage", "店舗を登録しました。");    
        
        return "redirect:/admin/stores";
    }  
    
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable(name = "id") Integer id, Model model) {
    	StoreInformation store = storeInformationRepository.getReferenceById(id);
        String imageName = store.getImageName();
        StoreEditForm storeEditForm = new StoreEditForm(store.getStoreId(), store.getCategoryId(), store.getStoreName(), null, store.getRemarks(), store.getPriceLowerLimit(), store.getPriceUpperLimit(), store.getBusinessHoursOpen(), store.getBusinessHoursClose(), store.getRegularHoliday(), store.getPostalCode(), store.getAddress(), store.getPhoneNumber());
        
    	List<Category> category;
    	category = categoryRepository.findAll();
    	
    	model.addAttribute("category", category);
        model.addAttribute("imageName", imageName);
        model.addAttribute("storeEditForm", storeEditForm);
        
        return "admin/stores/edit";
    }  
    
    @PostMapping("/{id}/update")
    public String update(@ModelAttribute @Validated StoreEditForm storeEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam(name = "category", required = false) Integer categoryId,
    		@RequestParam(name = "businessHoursOpen", required = false) Integer businessHoursOpen, @RequestParam(name = "businessHoursClose", required = false) Integer businessHoursClose, Model model) {        
        
    	MultipartFile imageFile = storeEditForm.getImageFile();
        if (imageFile.isEmpty()) {
            // imageFileが空の場合、BindingResultにエラーを追加
            bindingResult.rejectValue("imageFile", "error.imageFile", "画像ファイルを選択してください。");
        }
    	if (bindingResult.hasErrors()) {
        	List<Category> category;
        	category = categoryRepository.findAll();
        	model.addAttribute("category", category);
            return "admin/stores/edit";
        }
        
        storeService.update(storeEditForm, categoryId, businessHoursOpen, businessHoursClose);
        redirectAttributes.addFlashAttribute("successMessage", "店舗情報を編集しました。");
        
        return "redirect:/admin/stores";
    }    
    
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {        
    	storeInformationRepository.deleteById(id);
                
        redirectAttributes.addFlashAttribute("successMessage", "店舗を削除しました。");
        
        return "redirect:/admin/stores";
    }  
     
}

package com.example.nagoyameshi.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.repository.FavoriteRepository;
import com.example.nagoyameshi.repository.StoreInformationRepository;
import com.example.nagoyameshi.security.UserDetailsImpl;
import com.example.nagoyameshi.service.FavoriteService;

@Controller
public class FavoriteController {
	private final FavoriteRepository favoriteRepository;
	private final StoreInformationRepository storeInformationRepository;
	private final FavoriteService favoriteService;
	
	public FavoriteController(FavoriteRepository favoriteRepository, StoreInformationRepository storeInformationRepository, FavoriteService favoriteService) {
		this.favoriteRepository = favoriteRepository;
		this.favoriteService = favoriteService;
		this.storeInformationRepository = storeInformationRepository;
	}
	
	@GetMapping("/favorite")
	 public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, @PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable, Model model) {
		User user = userDetailsImpl.getUser();
		Page<Favorite> favoritePage = favoriteRepository.findByUserOrderByRegistrationAtDesc(user, pageable);
		model.addAttribute("favoritePage", favoritePage); 
		
		return "favorite/index";
		
	}
	
	@PostMapping("/store/{storeId}/favorite/create")
	public String create(@PathVariable(name = "storeId") Integer storeId,@AuthenticationPrincipal UserDetailsImpl userDetailsImpl,RedirectAttributes redirectAttributes,Model model, @RequestParam("add") String topicPath) {
		StoreInformation store = storeInformationRepository.getReferenceById(storeId);
		User user = userDetailsImpl.getUser();
		
		favoriteService.create(store, user);
		redirectAttributes.addFlashAttribute("successMessage", "お気に入り一覧に追加しました。");
		redirectAttributes.addAttribute("topicPath", topicPath);

		return "redirect:/searchDetail/{storeId}?add={topicPath}";
	}
	@PostMapping("/store/{storeId}/favorite/{favoriteId}/delete")
	public String delete(@PathVariable(name = "favoriteId") Integer favoriteId, @RequestParam("add") String topicPath ,RedirectAttributes redirectAttributes, Model model) {
		favoriteRepository.deleteById(favoriteId);
		redirectAttributes.addFlashAttribute("successMessage", "お気に入りを解除しました。");
		redirectAttributes.addAttribute("topicPath", topicPath);

		return "redirect:/searchDetail/{storeId}?add={topicPath}";
	}
}

package com.example.nagoyameshi.service;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.repository.FavoriteRepository;

import jakarta.transaction.Transactional;
@Service
public class FavoriteService {
	private final FavoriteRepository favoriteRepository;
	
	public FavoriteService(FavoriteRepository favoriteRepository) {
		this.favoriteRepository = favoriteRepository;
	}
	@Transactional
	public void create(StoreInformation store, User user) {
		Favorite favorite = new Favorite();
		favorite.setStore(store);
		favorite.setUser(user);
		
		favoriteRepository.save(favorite);
	}
	public boolean isFavorite(StoreInformation store, User user) {
		return favoriteRepository.findByStoreAndUser(store, user) != null;
		
	}
}

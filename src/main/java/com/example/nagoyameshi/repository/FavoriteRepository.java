package com.example.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Favorite;
import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite,Integer> {
	public Favorite findByStoreAndUser(StoreInformation store, User user);

	public Page<Favorite> findByUserOrderByRegistrationAtDesc(User user, Pageable pageable);
}

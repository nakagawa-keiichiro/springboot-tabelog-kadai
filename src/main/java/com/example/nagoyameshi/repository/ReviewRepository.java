package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.entity.User;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
	public Review findByStoreAndUser(StoreInformation store,User user);

	public Page<Review> findByStoreOrderByRegistrationAtDesc(StoreInformation store, Pageable pageable);
	
	public Page<Review> findByStoreOrderByEvaluationDesc(StoreInformation store, Pageable pageable);

	public List<Review> findByStoreOrderByRegistrationAtDesc(StoreInformation store);
}

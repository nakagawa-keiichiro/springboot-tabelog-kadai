package com.example.nagoyameshi.service;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.StoreInformation;
import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.form.ReviewEditForm;
import com.example.nagoyameshi.form.ReviewForm;
import com.example.nagoyameshi.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	@Transactional
	public void create(StoreInformation store,User user,ReviewForm reviewForm) {
		Review review = new Review();
		review.setStore(store);
		review.setUser(user);
		review.setEvaluation(reviewForm.getEvaluation());
		review.setComments(reviewForm.getComments());
		reviewRepository.save(review);
	}
	
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());
		review.setEvaluation(reviewEditForm.getEvaluation());
		review.setComments(reviewEditForm.getComments());
		reviewRepository.save(review);
	}
	public boolean hasUserAlreadyReviewed(StoreInformation store, User user) {
        return reviewRepository.findByStoreAndUser(store, user) != null;
    }
}

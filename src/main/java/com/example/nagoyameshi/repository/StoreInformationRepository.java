package com.example.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.nagoyameshi.entity.StoreInformation;

public interface StoreInformationRepository extends JpaRepository<StoreInformation, Integer> {
	public Page<StoreInformation> findByStoreNameLike(String keyword, Pageable pageable);
    public Page<StoreInformation> findByStoreNameLikeOrAddressLikeOrderByRegistrationAtDesc(String nameKeyword, String addressKeyword, Pageable pageable);  
    public Page<StoreInformation> findByStoreNameLikeOrAddressLikeOrderByPriceLowerLimitAsc(String nameKeyword, String addressKeyword, Pageable pageable);  
    public Page<StoreInformation> findByAddressLikeOrderByRegistrationAtDesc(String area, Pageable pageable);
    public Page<StoreInformation> findByAddressLikeOrderByPriceLowerLimitAsc(String area, Pageable pageable);
    public Page<StoreInformation> findByPriceLowerLimitLessThanEqualOrderByRegistrationAtDesc(Integer price, Pageable pageable);
    public Page<StoreInformation> findByPriceLowerLimitLessThanEqualOrderByPriceLowerLimitAsc(Integer price, Pageable pageable); 
    public Page<StoreInformation> findAllByOrderByRegistrationAtDesc(Pageable pageable);
    public Page<StoreInformation> findAllByOrderByPriceLowerLimitAsc(Pageable pageable);   
    public Page<StoreInformation> findByCategoryIdOrderByRegistrationAtDesc(Integer id, Pageable pageable);
    public Page<StoreInformation> findByCategoryIdOrderByPriceLowerLimitAsc(Integer id, Pageable pageable);
    
	@Query(value ="select * from store_information where store_id = :id ", nativeQuery = true)
	public StoreInformation findByStoreId(@Param("id")Integer id);
}

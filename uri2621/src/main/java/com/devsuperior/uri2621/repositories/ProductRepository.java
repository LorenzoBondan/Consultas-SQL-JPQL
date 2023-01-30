package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {

	// SQL
	@Query(nativeQuery = true, value = "SELECT products.name from products "
			+ "INNER JOIN providers "
			+ "ON providers.id = products.id_providers "
			+ "WHERE providers.name LIKE CONCAT(:startsWith, '%') "
			+ "and products.amount BETWEEN :minAmount AND :maxAmount")
	List<ProductMinProjection> search1(String startsWith, int minAmount, int maxAmount);
	
	// JPQL
	@Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) " // CONSTRUTOR ProductMinDTO(String name)
			+ "FROM Product obj "
			+ "WHERE obj.provider.name LIKE CONCAT(:startsWith, '%') "
			+ "and obj.amount BETWEEN :minAmount AND :maxAmount")
	List<ProductMinDTO> search2(String startsWith, int minAmount, int maxAmount);
}

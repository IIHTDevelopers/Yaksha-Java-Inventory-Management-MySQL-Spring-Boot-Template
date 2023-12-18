package com.inventorymanagement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventorymanagement.dto.ProductDTO;
import com.inventorymanagement.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public List<ProductDTO> getAllProducts() {
		// write your logic here
		return null;
	}

	@Override
	public ProductDTO getProductById(Long id) {
		// write your logic here
		return null;
	}

	@Override
	@Transactional
	public ProductDTO createProduct(ProductDTO productDTO) {
		// write your logic here
		return null;
	}

	@Override
	@Transactional
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		// write your logic here
		return null;
	}

	@Override
	@Transactional
	public boolean deleteProduct(Long id) {
		// write your logic here
		return false;
	}
}

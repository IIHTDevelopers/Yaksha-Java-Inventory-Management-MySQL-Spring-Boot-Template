package com.inventorymanagement.service;

import java.util.List;
import com.inventorymanagement.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> getAllProducts();

	ProductDTO getProductById(Long id);

	ProductDTO createProduct(ProductDTO productDTO);

	ProductDTO updateProduct(Long id, ProductDTO productDTO);

	boolean deleteProduct(Long id);
}

package com.inventorymanagement.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.inventorymanagement.dto.ProductDTO;
import com.inventorymanagement.dto.SellDTO;

public class MasterData {

	public static ProductDTO getProductDTO() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(1L);
		productDTO.setName("Product 1");
		productDTO.setPrice(100.0);
		return productDTO;
	}

	public static SellDTO getSellDTO() {
		SellDTO sellDTO = new SellDTO();
		sellDTO.setId(1L);
		sellDTO.setProductId(1L);
		sellDTO.setQuantity(10);
		sellDTO.setOrderDate(new Date());
		return sellDTO;
	}

	public static List<ProductDTO> getProductDTOList() {
		List<ProductDTO> productDTOS = new ArrayList<>();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(1L);
		productDTO.setName("Product 1");
		productDTO.setPrice(100.0);
		productDTOS.add(productDTO);

		productDTO = new ProductDTO();
		productDTO.setId(2L);
		productDTO.setName("Product 2");
		productDTO.setPrice(200.0);
		productDTOS.add(productDTO);

		return productDTOS;
	}

	public static List<SellDTO> getSellDTOList() {
		List<SellDTO> sellDTOS = new ArrayList<>();

		SellDTO sellDTO = new SellDTO();
		sellDTO.setId(1L);
		sellDTO.setProductId(1L);
		sellDTO.setQuantity(10);
		sellDTO.setOrderDate(new Date());

		sellDTOS.add(sellDTO);

		sellDTO = new SellDTO();
		sellDTO.setId(2L);
		sellDTO.setProductId(2L);
		sellDTO.setQuantity(5);
		sellDTO.setOrderDate(new Date());

		sellDTOS.add(sellDTO);

		return sellDTOS;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		String s = "";
		for (int i = 0; i < size; i++) {
			s.concat("A");
		}
		return s;
	}

	public static Map<String, Integer> getProductSalesMap() {
		Map<String, Integer> getProductSalesMap = new HashMap<String, Integer>();
		getProductSalesMap.merge(getProductDTO().getName(), getSellDTO().getQuantity(), Integer::sum);
		return getProductSalesMap;
	}
}
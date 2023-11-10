package com.inventorymanagement.service;

import java.util.List;
import java.util.Map;

import com.inventorymanagement.dto.SellDTO;

public interface SellService {
	List<SellDTO> getAllSells();

	SellDTO getSellById(Long id);

	SellDTO createSell(SellDTO sellDTO);
	
	Map<String, Integer> getProductsSoldInLastMonth();
}

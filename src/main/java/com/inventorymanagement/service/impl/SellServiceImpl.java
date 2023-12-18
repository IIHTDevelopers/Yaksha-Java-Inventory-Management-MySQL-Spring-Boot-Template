package com.inventorymanagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventorymanagement.dto.SellDTO;
import com.inventorymanagement.service.SellService;

@Service
public class SellServiceImpl implements SellService {

	@Override
	public List<SellDTO> getAllSells() {
		// write your logic here
		return null;
	}

	@Override
	public SellDTO getSellById(Long id) {
		// write your logic here
		return null;
	}

	@Override
	@Transactional
	public SellDTO createSell(SellDTO sellDTO) {
		// write your logic here
		return null;
	}

	@Override
	public Map<String, Integer> getProductsSoldInLastMonth() {
		// write your logic here
		return null;
	}

}

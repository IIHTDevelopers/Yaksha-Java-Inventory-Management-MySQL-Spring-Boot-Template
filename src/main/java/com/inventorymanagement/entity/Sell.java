package com.inventorymanagement.entity;

import java.util.Date;

public class Sell {

	private Long id;
	private Long productId;
	private Integer quantity;
	private Date orderDate;

	public Sell() {
		super();
	}

	public Sell(Long id, Long productId, Integer quantity, Date orderDate) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.orderDate = orderDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Sell [id=" + id + ", productId=" + productId + ", quantity=" + quantity + ", orderDate=" + orderDate
				+ "]";
	}
}

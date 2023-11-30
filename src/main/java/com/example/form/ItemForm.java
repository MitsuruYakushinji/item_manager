package com.example.form;

// リクエストの送受信に必要なクラス
public class ItemForm {

	private String name;
	private Integer price;
	private Integer categoryId;
	
	// nameのゲッターセッター
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// priceのゲッターセッター
	public Integer getPrice() {
		return this.price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	// categoryIdのゲッターセッター
	public Integer getCategoryId() {
        return this.categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}

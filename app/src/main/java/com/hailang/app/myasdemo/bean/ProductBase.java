package com.hailang.app.myasdemo.bean;


public class ProductBase{
	//表主键
	long id;
	//商品Id
	String productId;
	//标题
	String title;
	//缩略图
	String productImgThumb;
	//图集
	String productImage;
	//兑换积分
	String exchangeValue="0";
	//原价
	String newPrice;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProductImgThumb() {
		return productImgThumb;
	}
	public void setProductImgThumb(String productImgThumb) {
		this.productImgThumb = productImgThumb;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getExchangeValue() {
		return exchangeValue;
	}
	public void setExchangeValue(String exchangeValue) {
		this.exchangeValue = exchangeValue;
	}
	public String getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(String newPrice) {
		this.newPrice = newPrice;
	}
	
}

package com.hailang.app.myasdemo.bean;

public class Product extends ProductBase {
	// 商品分类
	String category;
	String totalBuy;// 多少人兑换过
	String label;// 标签（空格隔开）
	String sellingPoint;// 卖点（空格隔开）
	String merchantServer;// 服务（空格隔开）
	String productDetalHtml;// 详情（页面）
	String presentValue;// 增送积分
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	public String getTotalBuy() {
		return totalBuy;
	}

	public void setTotalBuy(String totalBuy) {
		this.totalBuy = totalBuy;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSellingPoint() {
		return sellingPoint;
	}

	public void setSellingPoint(String sellingPoint) {
		this.sellingPoint = sellingPoint;
	}

	public String getMerchantServer() {
		return merchantServer;
	}

	public void setMerchantServer(String merchantServer) {
		this.merchantServer = merchantServer;
	}

	public String getProductDetalHtml() {
		return productDetalHtml;
	}

	public void setProductDetalHtml(String productDetalHtml) {
		this.productDetalHtml = productDetalHtml;
	}

	public String getPresentValue() {
		return presentValue;
	}

	public void setPresentValue(String presentValue) {
		this.presentValue = presentValue;
	}

}

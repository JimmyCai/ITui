package cn.itui.webdevelop.model;

import java.io.Serializable;

public class Stock implements Serializable{
	private static final long serialVersionUID = 1L;
	 
	private Long stockId;
	private String stockCode;
	private String stockName;
	
	public Stock(){}
	
	public Stock(Long stockId, String stockCode, String stockName) {
		this.stockId = stockId;
		this.stockCode = stockCode;
		this.stockName = stockName;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
}

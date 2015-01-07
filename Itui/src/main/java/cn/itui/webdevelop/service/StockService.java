package cn.itui.webdevelop.service;

import cn.itui.webdevelop.model.Stock;

public interface StockService {
	void save(Stock stock);
	void update(Stock stock);
	void delete(Stock stock);
	Stock findByStockCode(String stockCode);
}

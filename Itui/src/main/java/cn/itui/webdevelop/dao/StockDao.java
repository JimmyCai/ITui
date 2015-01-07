package cn.itui.webdevelop.dao;

import cn.itui.webdevelop.model.Stock;

public interface StockDao {
	void save(Stock stock);
	void update(Stock stock);
	void delete(Stock stock);
	Stock findByStockCode(String stockCode);
}

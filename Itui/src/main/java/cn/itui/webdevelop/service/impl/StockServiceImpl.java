package cn.itui.webdevelop.service.impl;

import cn.itui.webdevelop.dao.StockDao;
import cn.itui.webdevelop.model.Stock;
import cn.itui.webdevelop.service.StockService;

public class StockServiceImpl implements StockService{
	StockDao stockDao;

	public void save(Stock stock) {
		this.stockDao.save(stock);
	}

	public void update(Stock stock) {
		this.stockDao.update(stock);
	}

	public void delete(Stock stock) {
		this.stockDao.delete(stock);
	}

	public Stock findByStockCode(String stockCode) {
		return this.stockDao.findByStockCode(stockCode);
	}

	public StockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

}

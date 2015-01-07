package cn.itui.webdevelop.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import cn.itui.webdevelop.dao.StockDao;
import cn.itui.webdevelop.model.Stock;

public class StockDaoImpl implements StockDao{
	private SessionFactory sessionFactory;

	public void save(Stock stock) {
		Session session = sessionFactory.getCurrentSession();
		session.save(stock);		
	}

	public void update(Stock stock) {
		Session session = sessionFactory.getCurrentSession();
		session.update(stock);
	}

	public void delete(Stock stock) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(stock);
	}

	public Stock findByStockCode(String stockCode) {
		Session session = sessionFactory.getCurrentSession();
		List list = session.createCriteria(Stock.class).add(Restrictions.sqlRestriction("STOCK_CODE=" + stockCode)).list();
		return (Stock)list.get(0);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

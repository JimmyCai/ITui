package cn.itui.webdevelop.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itui.webdevelop.model.Stock;
import cn.itui.webdevelop.service.StockService;

@Controller
public class StockController {
	private static final Logger logger = Logger.getLogger(StockController.class);
	private StockService stockService;
	
	@RequestMapping(value=URLConstants.GETSTOCK)
	public void getStockBy(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in.................");
		String requestCode = request.getParameter("code");
		logger.info("get stock by code, code:" + requestCode);
		Stock stock = stockService.findByStockCode(requestCode);
		try {
			System.out.println(stock.getStockName());
			response.getWriter().print(stock.getStockName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public StockService getStockService() {
		return stockService;
	}

	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}

}

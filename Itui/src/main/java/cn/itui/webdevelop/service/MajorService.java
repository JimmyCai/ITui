package cn.itui.webdevelop.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
public interface MajorService {
	public String searchMajorsList(String condition, String category, String subject, String major_type, String college_type, String area);
}

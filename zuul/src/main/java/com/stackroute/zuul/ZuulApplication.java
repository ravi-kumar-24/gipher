package com.stackroute.zuul;

import com.stackroute.zuul.filters.JWTFilters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter(){
		final FilterRegistrationBean filterBean=new FilterRegistrationBean();
		filterBean.setFilter(new JWTFilters());
		filterBean.addUrlPatterns("/giphermanager/api/v1/gifmanager/*","/gipherrecommendations/api/v1/recommendations/*");
		return filterBean;
	}

}

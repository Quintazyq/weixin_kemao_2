package org.quintajava.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@SpringBootApplication
public class weixinApplication {
	
	@Bean
	public XmlMapper xmlMapper() {
		XmlMapper mapper=new XmlMapper();
		return mapper;
		
	}

	public static void main(String[] args) {
		SpringApplication.run(weixinApplication.class, args);
	}

}

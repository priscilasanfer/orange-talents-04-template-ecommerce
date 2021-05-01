package br.com.zupacademy.priscila.mercadolivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.TimeZone;

@SpringBootApplication
@EnableFeignClients
public class MercadoLivreApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(MercadoLivreApplication.class, args);
	}

	@FeignClient("name")
	static interface NameService {
		@RequestMapping("/")
		public String getName();
	}
}

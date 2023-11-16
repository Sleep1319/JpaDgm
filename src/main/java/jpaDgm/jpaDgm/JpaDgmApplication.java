package jpaDgm.jpaDgm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;

@SpringBootApplication
public class JpaDgmApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaDgmApplication.class, args);
	}
//	@Bean
//	public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
//		return new HiddenHttpMethodFilter();
//	}
}

package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 이 어노테이션이 내부적으로 @ComponentScan, @Configuration 을 가지고 있기 때문에
// 스프링 부트로 구동시 스프링 컨테이너를 실행하고 스프링 빈을 자동으로 등록한다.
@SpringBootApplication
public class CoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}

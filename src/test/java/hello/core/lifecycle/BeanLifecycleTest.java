package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifecycleTest {
    @Test
    public void lifecycleTest() {
        // ConfigurableApplicationContext는 AnnotationConfigApplicationContext의 상위 인터페이스임
        // close()를 호출하기 위해서 사용함.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifecycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifecycleConfig {
        // 2. 스프링 전용 인터페이스에 의존하지 않아 외부라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.
        // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}

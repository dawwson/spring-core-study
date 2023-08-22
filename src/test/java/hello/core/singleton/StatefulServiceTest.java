package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


class StatefulServiceTest {
    @Test
    @DisplayName("특정 클라이언트가 값을 변경할 수 있는 필드가 있을 때")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);
/*
        // ThreadA: A 사용자 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB: B 사용자 20000원 주문
        statefulService2.order("userB", 20000); // <= 중간에 B 사용자 요청으로 price 값이 바뀐다.

        // 사용자 A의 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);  // 10000이 아니라 20000 => 실제 상황이라면 서비스 망함...

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
 */
        int userAPrice = statefulService1.order("userA", 10000);
        int userBPrice = statefulService2.order("userB", 20000);
        System.out.println("B price = " + userAPrice);
        System.out.println("A price = " + userBPrice);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
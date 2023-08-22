package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 어떤 구현체를 넣어줄지(주입, injection)는 외부 AppConfig에서 결정한다. => DIP 만족
// 서비스 단에서는 어떤 구현체가 들어오는지 알 필요가 없고, 비즈니스 로직 실행에만 집중하면 된다. => 관심사 분리
// 의존 관계를 외부에서 주입하는 것을 DI(Dependency Injection)이라고 한다.
// 역할과 구현 클래스 한 눈에 보임(애플리케이션 전체 구성이 한 눈에 보임)
// 이렇게 객체를 생성하고 관리하며 의존관계를 연결해주는 AppConfig와 같은 것을 "DI 컨테이너"라고 한다.
// DI 컨테이너는 실행시점에 동적으로 의존 관계를 설정해준다.
@Configuration
public class AppConfig {
    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // new MemoryMemberRepository()를 두 번 호출하면 싱글톤이 깨지는 것인가? => NO!
    /*
       AppConfig를 상속받은 AppConfig@CGLIB 클래스에서
       내가 등록한 Bean을 오버라이딩하여 아래와 같은 코드를 실행할 것이다.
       if (memoryMemberRepository가 이미 있으면) {
           return 스프링 컨테이너에서 찾아서 반환;
       } else { // 스프링 컨테이너에 없으면
           // 기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
           return 반환;
       }
    */

    @Bean  // 컨테이너에 등록된다.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();  // NOTE: 할인 정책 변경
    }
}

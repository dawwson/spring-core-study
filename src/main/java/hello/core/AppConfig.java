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

// 어떤 구현체를 넣어줄지(주입, injection)는 외부 AppConfig에서 결정한다. => DIP 만족
// 서비스 단에서는 어떤 구현체가 들어오는지 알 필요가 없고, 비즈니스 로직 실행에만 집중하면 된다. => 관심사 분리
// 의존 관계를 외부에서 주입하는 것을 DI(Dependency Injection)이라고 한다.
// 역할과 구현 클래스 한 눈에 보임(애플리케이션 전체 구성이 한 눈에 보임)
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();  // NOTE: 할인 정책 변경
    }
}

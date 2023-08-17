package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 어떤 구현체를 넣어줄지(주입, injection)는 외부 AppConfig에서 결정한다. => DIP 만족
// 서비스 단에서는 어떤 구현체가 들어오는지 알 필요가 없고, 비즈니스 로직 실행에만 집중하면 된다. => 관심사 분리
// 의존 관계를 외부에서 주입하는 것을 DI(Dependency Injection)이라고 한다.
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}

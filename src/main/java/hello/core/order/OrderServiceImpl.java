package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor  // => final 이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.
public class OrderServiceImpl implements OrderService {
    // 1과 2를 통해 OCP, DIP 만족
    // 1. 추상에만 의존하도록 수정
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 2. 생성자를 통해 의존성을 주입하도록 수정
    @Autowired
    public OrderServiceImpl(
        MemberRepository memberRepository,
//        @Qualifier("mainDiscountPolicy")  // 동일 이름으로 매칭되는 Qualifier를 찾아서 주입힌다.
        @MainDiscountPolicy
        DiscountPolicy discountPolicy
    ) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}

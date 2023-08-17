package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {
    // 1과 2를 통해 OCP, DIP 만족
    // 1. 추상에만 의존하도록 수정
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    // 2. 생성자를 통해 의존성을 주입하도록 수정
    public OrderServiceImpl(
        MemberRepository memberRepository,
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

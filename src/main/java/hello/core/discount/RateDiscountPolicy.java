package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
//@Primary  // 우선권을 주는 방식(좀 더 코드가 깔끔하나, Qualifier와 같이 쓰면 Qualifier의 우선권이 높다)
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPercent = 10;

    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}

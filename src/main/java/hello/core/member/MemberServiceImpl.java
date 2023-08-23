package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor  // => final 이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.
public class MemberServiceImpl implements MemberService {
    // 1과 2를 통해 OCP, DIP 만족
    // 1. 추상에만 의존하도록 수정
    private final MemberRepository memberRepository;
/*
    // 2. 생성자를 통해 의존성을 주입하도록 수정
    @Autowired  // 스프링이 컴포넌트 스캔으로 알아서 빈으로 등록하고 의존성 주입해줌
    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자 주입
        this.memberRepository = memberRepository;
    }
*/
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

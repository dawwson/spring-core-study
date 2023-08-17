package hello.core.member;

public class MemberServiceImpl implements MemberService {
    // 1과 2를 통해 OCP, DIP 만족
    // 1. 추상에만 의존하도록 수정
    private final MemberRepository memberRepository;

    // 2. 생성자를 통해 의존성을 주입하도록 수정
    public MemberServiceImpl(MemberRepository memberRepository) { // 생성자 주입
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

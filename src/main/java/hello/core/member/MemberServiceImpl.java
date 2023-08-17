package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 다형성에 의해서 MemoryMemberRepository에 구현된 save, findMember가 호출된다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters =
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = Configuration.class  // => 예제에 있던 @Configuration 클래스들 제외
                )
)
public class AutoAppConfig {
    /*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }
    */
    // 수동 빈 등록과 자동 빈 등록 시 이름이 충돌하면 에러를 낸다.
    // Consider renaming one of the beans or enabling overriding by setting
    // spring.main.allow-bean-definition-overriding=true
}

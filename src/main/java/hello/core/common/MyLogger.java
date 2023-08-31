package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
/*
value = "request"
=> HTTP 요청 당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸됨

proxyMode = ScopedProxyMode.TARGET_CLASS
=> MyLogger를 상속받은 가짜 프록시 클래스의 객체를 만들어서 빈을 등록하고, 의존성을 주입한다.
   클라이언트의 요청이 오면 그때 내부적으로 진짜 빈을 요청하는 위임 로직이 들어있다.
   싱글톤이 아닌데 싱글톤처럼 사용할 수 있게 한다.

** 핵심 : Provider든 Proxy든 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점이다. **
*/
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "][" + requestUrl + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}

package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 1. InitializingBean, DisposableBean => 스프링 전용 인터페이스에 의존하게 되는 단점
//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message: " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // @PostContstuct, @PreDestroy => 가장 권장하는 방법
    // 스프링 종속이 아니라 자바 표준임
    @PostConstruct
    // 초기화 콜백 : 의존 관계 셋팅이 끝나면 호출됨
    public void init() throws Exception {
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    // 소멸전 콜백 : 빈이 소멸되기 직전에 호출
    public void close() throws Exception {
        disconnect();
    }
}

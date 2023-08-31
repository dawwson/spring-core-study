package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final ObjectProvider<MyLogger> myLoggerObjectProvider;

    public void someLogic(String id) {
        // 컨트롤러에서 찍힌 로그와 같은 UUID이다.
        // 서비스에서 myLogger를 요청해도 같은 request scope 안에서 동일한 객체이다.
        MyLogger myLogger = myLoggerObjectProvider.getObject();
        myLogger.log("service id = " + id);
    }
}

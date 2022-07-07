package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RestController
@Slf4j
public class RequestHeaderController {

    @RequestMapping("/headers")
    //다양한 파라미터를 받을 수 있음
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          //이 애너테이션을 통해서
                          @RequestHeader MultiValueMap<String,String> headerMap,
                          //host라는 필수 헤더 정보를 받을 수 있음음
                          @RequestHeader("host") String host,
                          //쿠키 정보도 얻을 수 있음 required = false는 필수는 아니라는 것임
                          @CookieValue(value = "myCookie",required = false) String cookie){
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";

    }
}

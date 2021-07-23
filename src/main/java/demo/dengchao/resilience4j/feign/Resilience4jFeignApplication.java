package demo.dengchao.resilience4j.feign;

import demo.dengchao.resilience4j.feign.broken.BrokenHttpBinService;
import demo.dengchao.resilience4j.feign.broken.ExtendedBrokenHttpBinService;
import demo.dengchao.resilience4j.feign.works.ExtendedWorksHttpBinService;
import demo.dengchao.resilience4j.feign.works.WorksHttpBinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;

@Slf4j
@EnableFeignClients
@SpringBootApplication
@RequiredArgsConstructor
public class Resilience4jFeignApplication {

    private final WorksHttpBinService works;
    private final BrokenHttpBinService broken;
    private final ExtendedWorksHttpBinService extendWorks;
    private final ExtendedBrokenHttpBinService extendBroken;

    public static void main(String[] args) {
        SpringApplication.run(Resilience4jFeignApplication.class, args);
    }

    @PostConstruct
    private void postConstruct() {
        works.ip();
        extendWorks.ip();

        if (broken.ip().getOrigin().equals("localhost")) {
            log.error("Oh no, Resilience4j time limiter fallback triggered even not timeout.");
        }
        if (extendBroken.ip().getOrigin().equals("localhost")) {
            log.error("Oh no, Resilience4j time limiter fallback triggered even not timeout.");
        }
    }
}

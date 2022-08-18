package demo.dengchao.resilience4j.feign;

import demo.dengchao.resilience4j.feign.broken.Ip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestTemplateWarmup {

    @NonNull
    private final RestTemplate template;

    @NonNull
    private final Resilience4JCircuitBreakerFactory factory;

    @PostConstruct
    public void postConstruct() {
        // See https://spring.io/projects/spring-cloud-circuitbreaker#core-concepts
        // the factory has a default 1 second timeout limiter
        factory.create("rest-template")
                .run(() -> {
                    int delay = 0;
                    Ip response = template.getForObject("https://httpbin.org/delay/{delay}", Ip.class, delay);
                    log.info("rest template works: {}", response);
                    return response;
                }, (throwable) -> {
                    log.warn("No, this should not happen", throwable);
                    return Ip.LOCALHOST;
                });

    }
}

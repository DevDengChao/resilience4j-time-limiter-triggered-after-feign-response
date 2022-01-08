package demo.dengchao.resilience4j.feign;

import demo.dengchao.resilience4j.feign.broken.BrokenHttpBinService;
import demo.dengchao.resilience4j.feign.broken.ExtendedBrokenHttpBinService;
import demo.dengchao.resilience4j.feign.works.ExtendedWorksHttpBinService;
import demo.dengchao.resilience4j.feign.works.WorksHttpBinService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@ConditionalOnProperty(value = "feign.clients.warm.up.enabled", matchIfMissing = true)
@RequiredArgsConstructor
public class FeignClientsWarmup {

    private final WorksHttpBinService works;
    private final BrokenHttpBinService broken;
    private final ExtendedWorksHttpBinService extendWorks;
    private final ExtendedBrokenHttpBinService extendBroken;

    @Getter
    private boolean success = true;

    @PostConstruct
    private void postConstruct() {
        works.ip();
        extendWorks.ip();

        if (broken.ip().getOrigin().equals("localhost")) {
            log.error("Oh no, Resilience4j time limiter fallback triggered even not timeout.");
            success = false;
        }
        if (extendBroken.ip().getOrigin().equals("localhost")) {
            log.error("Oh no, Resilience4j time limiter fallback triggered even not timeout.");
            success = false;
        }
    }

}

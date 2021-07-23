package demo.dengchao.resilience4j.feign.works;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.web.bind.annotation.GetMapping;

public interface BaseWorksHttpBinService {

    /**
     * Resilience4j's time limiter WILL NOT BE TRIGGERED if the method returned with 'void'
     */
    @GetMapping("/ip")
    void ip();

    @Slf4j
    class BaseWorksFallback implements BaseWorksHttpBinService {

        @Override
        public void ip() {
            log.info("fallback triggered when fetching ip from https://httpbin.org/ip");
        }
    }

    @Slf4j
    @RequiredArgsConstructor
    class BaseWorksFallbackFactory implements FallbackFactory<BaseWorksHttpBinService> {

        private final BaseWorksFallback fallback;

        @Override
        public BaseWorksHttpBinService create(Throwable cause) {
            log.warn("", cause);
            return fallback;
        }
    }
}

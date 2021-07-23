package demo.dengchao.resilience4j.feign.broken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;

public interface BaseBrokenHttpBinService {

    /**
     * Resilience4j's time limiter WILL BE TRIGGERED if the method NOT returned with 'void'
     */
    @NonNull
    @GetMapping("/ip")
    Ip ip();

    @Slf4j
    abstract class BaseBrokenFallback implements BaseBrokenHttpBinService {

        @NonNull
        @Override
        public Ip ip() {
            return Ip.LOCALHOST;
        }
    }

    @Slf4j
    @RequiredArgsConstructor
    class BaseBrokenFallbackFactory implements FallbackFactory<BaseBrokenHttpBinService> {

        private final BaseBrokenFallback fallback;

        @Override
        public BaseBrokenFallback create(Throwable cause) {
            log.warn("This should not happen indeed ", cause);
            return fallback;
        }
    }

}

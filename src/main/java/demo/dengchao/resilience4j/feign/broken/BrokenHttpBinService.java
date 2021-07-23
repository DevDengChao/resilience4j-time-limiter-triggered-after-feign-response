package demo.dengchao.resilience4j.feign.broken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "broken-http-bin", url = "https://httpbin.org",
        fallbackFactory = BrokenHttpBinService.BrokenFallbackFactory.class)
public interface BrokenHttpBinService {

    @GetMapping("/ip")
    Ip ip();

    @Component
    class BrokenFallback implements BrokenHttpBinService {

        @Override
        public Ip ip() {
            return Ip.LOCALHOST;
        }
    }

    @Slf4j
    @Component
    @RequiredArgsConstructor
    class BrokenFallbackFactory implements FallbackFactory<BrokenHttpBinService> {

        private final BrokenFallback fallback;

        @Override
        public BrokenHttpBinService create(Throwable cause) {
            log.warn("", cause);
            return fallback;
        }
    }

}

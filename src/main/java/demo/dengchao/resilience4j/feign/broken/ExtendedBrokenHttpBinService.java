package demo.dengchao.resilience4j.feign.broken;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(value = "extended-broken-http-bin", url = "https://httpbin.org",
        fallbackFactory = ExtendedBrokenHttpBinService.ExtendedBrokenFallbackFactory.class)
public interface ExtendedBrokenHttpBinService extends BaseBrokenHttpBinService {

    @Component
    class ExtendedBrokenFallback extends BaseBrokenFallback {
    }

    @Slf4j
    @Component
    class ExtendedBrokenFallbackFactory extends BaseBrokenFallbackFactory {

        public ExtendedBrokenFallbackFactory(ExtendedBrokenFallback fallback) {
            super(fallback);
        }
    }

}

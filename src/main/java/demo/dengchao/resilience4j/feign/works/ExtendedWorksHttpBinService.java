package demo.dengchao.resilience4j.feign.works;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@FeignClient(value = "extended-works-http-bin", url = "https://httpbin.org",
        fallbackFactory = ExtendedWorksHttpBinService.ExtendedWorksFallbackFactory.class)
public interface ExtendedWorksHttpBinService extends BaseWorksHttpBinService {

    @Component
    class ExtendedWorksFallback extends BaseWorksFallback {
    }

    @Component
    class ExtendedWorksFallbackFactory extends BaseWorksFallbackFactory {

        public ExtendedWorksFallbackFactory(ExtendedWorksFallback fallback) {
            super(fallback);
        }
    }
}

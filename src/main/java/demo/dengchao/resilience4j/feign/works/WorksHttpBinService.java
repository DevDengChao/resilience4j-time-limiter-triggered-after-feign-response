package demo.dengchao.resilience4j.feign.works;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "works-http-bin", url = "https://httpbin.org",
        fallbackFactory = WorksHttpBinService.WorksFallbackFactory.class)
public interface WorksHttpBinService {

    @GetMapping("/ip")
    void ip();

    @Component
    class WorksFallback implements WorksHttpBinService {

        @Override
        public void ip() {
        }
    }

    @Slf4j
    @Component
    @RequiredArgsConstructor
    class WorksFallbackFactory implements FallbackFactory<WorksHttpBinService> {

        private final WorksFallback fallback;

        @Override
        public WorksHttpBinService create(Throwable cause) {
            log.warn("", cause);
            return fallback;
        }
    }

}

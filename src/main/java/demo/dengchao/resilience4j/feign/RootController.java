package demo.dengchao.resilience4j.feign;

import demo.dengchao.resilience4j.feign.broken.BrokenHttpBinService;
import demo.dengchao.resilience4j.feign.broken.ExtendedBrokenHttpBinService;
import demo.dengchao.resilience4j.feign.works.ExtendedWorksHttpBinService;
import demo.dengchao.resilience4j.feign.works.WorksHttpBinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RootController {

    private static final String REASON = "Oh no, Resilience4j time limiter fallback triggered even not timeout.";
    private final WorksHttpBinService works;
    private final BrokenHttpBinService broken;
    private final ExtendedWorksHttpBinService extendWorks;
    private final ExtendedBrokenHttpBinService extendBroken;

    @GetMapping
    private void onGet() {
        works.ip();
        extendWorks.ip();

        if (broken.ip().getOrigin().equals("localhost")) {
            log.error(REASON);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, REASON);
        }
        if (extendBroken.ip().getOrigin().equals("localhost")) {
            log.error(REASON);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, REASON);
        }
    }
}

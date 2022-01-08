package demo.dengchao.resilience4j.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FeignClientsWarmupTest {

    @Autowired
    private FeignClientsWarmup warmup;

    @Test
    void test() {
        // NOTE: this should pass
        assertTrue(warmup.isSuccess());
    }
}

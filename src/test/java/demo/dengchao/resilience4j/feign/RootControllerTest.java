package demo.dengchao.resilience4j.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest("feign.clients.warm.up.enabled=false") // disable FeignClientsWarmup component to reduce logs
@AutoConfigureMockMvc
class RootControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void test() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk());
    }
}

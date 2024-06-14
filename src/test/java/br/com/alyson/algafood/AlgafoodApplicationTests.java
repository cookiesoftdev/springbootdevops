package br.com.alyson.algafood;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("dev")
class AlgafoodApplicationTests {

    @Test
    void contextLoads() {
    }

}

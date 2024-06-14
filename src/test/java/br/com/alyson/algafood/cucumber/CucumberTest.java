package br.com.alyson.algafood.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "br.com.alyson.algafood.cucumber")
@ContextConfiguration(classes = CucumberSpringConfiguration.class)
public class CucumberTest {
}
package com.olegzet.spring.calculation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(
        title = "Calculation API",
        version = "1.0",
        description = "Calculation API provides calculating difficult expressions and getting results.",
        license = @License(name = "Apache 2.0", url = "http://foo.bar"),
        contact = @Contact(url = "http://olegzet.com", name = "Oleg", email = "oleg@olegzet.com")
)
)
public class CalculationApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculationApplication.class, args);
    }
}

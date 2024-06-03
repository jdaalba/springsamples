package com.example.demo;

import com.example.demo.config.BeanConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest//(classes = {BeanConfig.class, FooRepositoryImpl.class})
class DemoApplicationTests {

    @Autowired
    BeanConfig.ContractValidator contractValidator;

    @Test
    void contextLoads() {
        BeanConfig.Contract contract = new BeanConfig.Contract();
        contract.setSignDate(LocalDate.now().minusDays(1));
        contract.setLocation("Alicante");
        contract.setSalary(1_999);
        assertThat(contractValidator.isValid(contract)).isTrue();
    }

}

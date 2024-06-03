package com.jdaalba.springsamples.config;

import com.jdaalba.springsamples.domain.Foo;
import com.jdaalba.springsamples.domain.cqrs.CreateFoo;
import com.jdaalba.springsamples.domain.cqrs.FindAllFoos;
import com.jdaalba.springsamples.domain.cqrs.FindFoo;
import com.jdaalba.springsamples.domain.cqrs.FindFooByBar;
import com.example.demo.domain.handlers.*;
import com.jdaalba.springsamples.domain.port.in.CommandHandler;
import com.jdaalba.springsamples.domain.port.in.QueryHandler;
import com.jdaalba.springsamples.domain.port.in.QueryManager;
import com.jdaalba.springsamples.domain.port.out.FooRepository;
import com.jdaalba.springsamples.domain.handlers.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class BeanConfig {

    @Bean
    CommandHandler<CreateFoo, UUID> createFooCommandHandler(FooRepository repository) {
        return new CreateFooHandler(repository);
    }

    @Bean
    QueryHandler<FindAllFoos, Foo> findAllFoosFooQueryHandler(FooRepository fooRepository) {
        return new FindAllFoosHandler(fooRepository);
    }

    @Bean
    QueryHandler<FindFoo, Foo> findFooFooQueryHandler(FooRepository repository) {
        return new FindFooHandler(repository);
    }

    @Bean
    @Primary
    QueryHandler<FindFooByBar, Foo> findFooByBarFooQueryHandler(FooRepository repository) {
        return new FindFooByBarHandler(repository);
    }

    @Bean
    @SuppressWarnings("unchecked")
    QueryManager queryManager(
            List<QueryHandler> handlers,
            Map<String, QueryHandler> handlersMap
    ) {
        return new FacadeQueryManager(
                handlers.stream().collect(Collectors.toMap(QueryHandler::getQueryClass, Function.identity()))
        );
    }

    @Bean
    Sampler sampler() {
        return new FilterSampler();
    }

    @Bean
    Sampler decoratorSampler(Sampler sampler) {
        return new ToUppercaseDecoratorSampler(sampler);
    }

    @Bean
    ContractValidator salaryValidator() {
        return new SalaryContractValidatorCor();
    }

    @Bean
    ContractValidator locationContractValidator(@Qualifier("salaryValidator") ContractValidator validator) {
        ContractValidator newValidator = new LocationContractValidatorCoR();
        newValidator.setValidator(validator);
        return newValidator;
    }

    @Bean
    @Primary
    ContractValidator dateValidator(@Qualifier("locationContractValidator") ContractValidator validator) {
        ContractValidator newValidator = new DateContractValidatorCoR();
        newValidator.setValidator(validator);
        return newValidator;
    }

    // para inyectar:
    // ponemos el nombre del bean
    // podemos poner el @Qualifier
    // podemos poner @Primary

    // CoR pattern
    public static abstract class ContractValidator {

        @Setter
        private ContractValidator validator;

        public final boolean isValid(Contract contract) {
            if (Objects.nonNull(validator)) {
                return validate(contract) && validator.validate(contract);
            } else {
                return validate(contract);
            }
        }

        protected abstract boolean validate(Contract contract);
    }

    @Data
    public static class Contract {
        LocalDate signDate;

        String location;

        double salary;
    }
}

interface Sampler {

    List<String> accept(List<String> strings);
}

class IdentitySampler implements Sampler {
    @Override
    public List<String> accept(List<String> strings) {
        return strings;
    }
}

class FilterSampler implements Sampler {
    @Override
    public List<String> accept(List<String> strings) {
        return strings.stream()
                .filter(s -> s.length() > 3)
                .toList();
    }
}

@RequiredArgsConstructor
// decorator
class ToUppercaseDecoratorSampler implements Sampler {

    private final Sampler sampler;

    @Override
    public List<String> accept(List<String> strings) {
        return sampler.accept(strings)
                .stream()
                .map(String::toUpperCase)
                .toList();
    }
}

// contratos:
// 1 - tiene que tener una fecha válida
// 2 - tiene que ser firmado en Alicante
// 3 - el salario debe ser inferior a 2.000


class DateContractValidatorCoR extends BeanConfig.ContractValidator {
    @Override
    protected boolean validate(BeanConfig.Contract contract) {
        return contract.getSignDate().isBefore(LocalDate.now());
    }
}

class LocationContractValidatorCoR extends BeanConfig.ContractValidator {
    @Override
    protected boolean validate(BeanConfig.Contract contract) {
        return contract.getLocation().equals("Alicante");
    }
}

class SalaryContractValidatorCor extends BeanConfig.ContractValidator {

    @Override
    protected boolean validate(BeanConfig.Contract contract) {
        return contract.getSalary() < 2_000;
    }
}

@Configuration
@Slf4j
class SampleConfig {

    @Autowired
    void foo(@Qualifier("findAllFoosFooQueryHandler") QueryHandler handler) {
        log.info("Found handler {}", handler);
    }

    @Autowired
    void foo2(QueryHandler findFooFooQueryHandler) {
        log.info("Found handler again {}", findFooFooQueryHandler);
    }
}

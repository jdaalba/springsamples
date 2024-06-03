package com.jdaalba.springsamples.domain.handlers;

import com.jdaalba.springsamples.domain.Foo;
import com.jdaalba.springsamples.domain.cqrs.FindFooByBar;
import com.jdaalba.springsamples.domain.cqrs.Query;
import com.jdaalba.springsamples.domain.port.in.QueryHandler;
import com.jdaalba.springsamples.domain.port.out.FooRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FindFooByBarHandler implements QueryHandler<FindFooByBar, Foo> {

    private final FooRepository repository;

    @Override
    public Class<? extends Query> getQueryClass() {
        return FindFooByBar.class;
    }

    @Override
    public Optional<Foo> findOne(FindFooByBar query) {
        return repository.findAll().stream()
                .filter(f -> f.bar().equals(query.bar()))
                .findFirst();
    }
}

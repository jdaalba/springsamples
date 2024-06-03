package com.example.demo.domain.handlers;

import com.example.demo.domain.Foo;
import com.example.demo.domain.cqrs.FindFooByBar;
import com.example.demo.domain.cqrs.Query;
import com.example.demo.domain.port.in.QueryHandler;
import com.example.demo.domain.port.out.FooRepository;
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

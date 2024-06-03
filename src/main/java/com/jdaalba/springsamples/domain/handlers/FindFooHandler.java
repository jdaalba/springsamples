package com.example.demo.domain.handlers;

import com.example.demo.domain.Foo;
import com.example.demo.domain.cqrs.FindFoo;
import com.example.demo.domain.cqrs.Query;
import com.example.demo.domain.port.in.QueryHandler;
import com.example.demo.domain.port.out.FooRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FindFooHandler implements QueryHandler<FindFoo, Foo> {

    private final FooRepository repository;

    @Override
    public Class<? extends Query> getQueryClass() {
        return FindFoo.class;
    }

    @Override
    public Optional<Foo> findOne(FindFoo query) {
        return repository.findOne(query.uuid());
    }
}

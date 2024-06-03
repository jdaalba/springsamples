package com.jdaalba.springsamples.domain.handlers;

import com.jdaalba.springsamples.domain.Foo;
import com.jdaalba.springsamples.domain.cqrs.FindFoo;
import com.jdaalba.springsamples.domain.cqrs.Query;
import com.jdaalba.springsamples.domain.port.in.QueryHandler;
import com.jdaalba.springsamples.domain.port.out.FooRepository;
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

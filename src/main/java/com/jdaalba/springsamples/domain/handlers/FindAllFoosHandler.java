package com.jdaalba.springsamples.domain.handlers;

import com.jdaalba.springsamples.domain.Foo;
import com.jdaalba.springsamples.domain.cqrs.FindAllFoos;
import com.jdaalba.springsamples.domain.cqrs.Query;
import com.jdaalba.springsamples.domain.port.in.QueryHandler;
import com.jdaalba.springsamples.domain.port.out.FooRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindAllFoosHandler implements QueryHandler<FindAllFoos, Foo> {

    private final FooRepository repository;

    @Override
    public Class<? extends Query> getQueryClass() {
        return FindAllFoos.class;
    }

    @Override
    public List<Foo> findAll(FindAllFoos query) {
        return repository.findAll();
    }
}

package com.example.demo.domain.handlers;

import com.example.demo.domain.Foo;
import com.example.demo.domain.cqrs.FindAllFoos;
import com.example.demo.domain.cqrs.Query;
import com.example.demo.domain.port.in.QueryHandler;
import com.example.demo.domain.port.out.FooRepository;
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

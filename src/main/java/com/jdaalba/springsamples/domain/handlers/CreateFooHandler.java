package com.example.demo.domain.handlers;

import com.example.demo.domain.Foo;
import com.example.demo.domain.cqrs.CreateFoo;
import com.example.demo.domain.port.in.CommandHandler;
import com.example.demo.domain.port.out.FooRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateFooHandler implements CommandHandler<CreateFoo, UUID> {

    private final FooRepository fooRepository;

    @Override
    public UUID accept(CreateFoo command) {
        return fooRepository.save(new Foo(null, command.bar())).uuid();
    }
}

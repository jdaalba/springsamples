package com.jdaalba.springsamples.domain.handlers;

import com.jdaalba.springsamples.domain.Foo;
import com.jdaalba.springsamples.domain.cqrs.CreateFoo;
import com.jdaalba.springsamples.domain.port.in.CommandHandler;
import com.jdaalba.springsamples.domain.port.out.FooRepository;
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

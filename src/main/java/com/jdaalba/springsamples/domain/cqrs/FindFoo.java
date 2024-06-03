package com.jdaalba.springsamples.domain.cqrs;

import com.jdaalba.springsamples.domain.Foo;

import java.util.UUID;

public record FindFoo(UUID uuid) implements Query<Foo> {
}

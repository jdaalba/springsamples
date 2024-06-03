package com.jdaalba.springsamples.domain.cqrs;

import com.jdaalba.springsamples.domain.Foo;

public record FindFooByBar(String bar) implements Query<Foo> {
}

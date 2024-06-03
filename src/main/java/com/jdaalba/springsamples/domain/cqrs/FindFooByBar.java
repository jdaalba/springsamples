package com.example.demo.domain.cqrs;

import com.example.demo.domain.Foo;

public record FindFooByBar(String bar) implements Query<Foo> {
}

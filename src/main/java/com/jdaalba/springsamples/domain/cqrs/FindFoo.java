package com.example.demo.domain.cqrs;

import com.example.demo.domain.Foo;

import java.util.UUID;

public record FindFoo(UUID uuid) implements Query<Foo> {
}

package com.example.demo.domain.port.out;

import com.example.demo.domain.Foo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FooRepository {

    Foo save(Foo foo);

    Optional<Foo> findOne(UUID uuid);

    List<Foo> findAll();
}

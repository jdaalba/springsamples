package com.example.demo.infrastructure.adapter;

import com.example.demo.domain.Foo;
import com.example.demo.domain.port.out.FooRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FooRepositoryImpl implements FooRepository {

    private final Map<UUID, Foo> fooMap = new HashMap<>();

    @Override
    public Foo save(Foo foo) {
        UUID key = UUID.randomUUID();
        fooMap.put(key, new Foo(key, foo.bar()));
        return fooMap.get(key);
    }

    @Override
    public Optional<Foo> findOne(UUID uuid) {
        return Optional.ofNullable(fooMap.get(uuid));
    }

    @Override
    public List<Foo> findAll() {
        return List.copyOf(fooMap.values());
    }
}

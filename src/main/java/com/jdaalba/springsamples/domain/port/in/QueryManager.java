package com.example.demo.domain.port.in;

import com.example.demo.domain.cqrs.Query;

import java.util.List;
import java.util.Optional;

public interface QueryManager {

    <T> Optional<T> findOne(Query<T> query);

    <T> List<T> findAll(Query<T> query);
}

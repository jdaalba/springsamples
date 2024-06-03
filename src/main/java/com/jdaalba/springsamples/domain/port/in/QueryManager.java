package com.jdaalba.springsamples.domain.port.in;

import com.jdaalba.springsamples.domain.cqrs.Query;

import java.util.List;
import java.util.Optional;

public interface QueryManager {

    <T> Optional<T> findOne(Query<T> query);

    <T> List<T> findAll(Query<T> query);
}

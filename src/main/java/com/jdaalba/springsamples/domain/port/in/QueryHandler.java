package com.jdaalba.springsamples.domain.port.in;

import com.jdaalba.springsamples.domain.cqrs.Query;

import java.util.List;
import java.util.Optional;

public interface QueryHandler<Q extends Query<R>, R> {

    Class<? extends Query> getQueryClass();

   default Optional<R> findOne(Q query) {
       throw new UnsupportedOperationException("not implemented");
   }

    default List<R> findAll(Q query) {
        throw new UnsupportedOperationException("not implemented");
    }
}

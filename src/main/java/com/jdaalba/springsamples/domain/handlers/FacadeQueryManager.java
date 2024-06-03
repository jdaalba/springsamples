package com.jdaalba.springsamples.domain.handlers;

import com.jdaalba.springsamples.domain.cqrs.Query;
import com.jdaalba.springsamples.domain.port.in.QueryHandler;
import com.jdaalba.springsamples.domain.port.in.QueryManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
// façade
// strategy: runtime elige el comportamiento
public class FacadeQueryManager implements QueryManager {

    private final Map<Class<? extends Query>, QueryHandler> handlers;

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> findOne(Query<T> query) {
        return (Optional<T>) handlers.get(query.getClass()).findOne(query);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findAll(Query<T> query) {
        return handlers.get(query.getClass()).findAll(query);
    }
}

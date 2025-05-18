package com.bcnc.r2dbc.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.ParameterizedType;
import java.util.function.Function;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public abstract class AdapterOperations<E, D, I, R extends ReactiveCrudRepository<D, I> & ReactiveQueryByExampleExecutor<D>> {

  protected R repository;
  protected ObjectMapper mapper;
  private Class<D> dataClass;
  private Function<D, E> toEntityFn;

  @SuppressWarnings("unchecked")
  protected AdapterOperations(R repository, ObjectMapper mapper, Function<D, E> toEntityFn) {
    this.repository = repository;
    this.mapper = mapper;
    ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
    this.dataClass = (Class<D>) genericSuperclass.getActualTypeArguments()[1];
    this.toEntityFn = toEntityFn;
  }

  protected D toData(E entity) {
    return mapper.convertValue(entity, dataClass);
  }

  protected E toEntity(D data) {
    return toEntityFn.apply(data);
  }

}

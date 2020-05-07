package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayersRepository extends CrudRepository<Players, Integer> {
    @Override
    <S extends Players> S save(S s);

    @Override
    <S extends Players> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Players> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<Players> findAll();

    @Override
    Iterable<Players> findAllById(Iterable<Integer> iterable);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Players players);

    @Override
    void deleteAll(Iterable<? extends Players> iterable);

    @Override
    void deleteAll();
}
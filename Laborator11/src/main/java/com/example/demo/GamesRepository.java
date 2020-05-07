package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GamesRepository extends CrudRepository<Games, Integer> {
    @Override
    <S extends Games> S save(S s);

    @Override
    <S extends Games> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Games> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<Games> findAll();

    @Override
    Iterable<Games> findAllById(Iterable<Integer> iterable);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Games games);

    @Override
    void deleteAll(Iterable<? extends Games> iterable);

    @Override
    void deleteAll();
}
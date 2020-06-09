package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScoreRepository extends CrudRepository<Score, Integer> {
    @Override
    <S extends Score> S save(S s);

    @Override
    <S extends Score> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Score> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    Iterable<Score> findAll();

    @Override
    Iterable<Score> findAllById(Iterable<Integer> iterable);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Score score);

    @Override
    void deleteAll(Iterable<? extends Score> iterable);

    @Override
    void deleteAll();

    Optional<Score> findByPlayerName(String string);

    Iterable<Score> findTop10ByOrderByScoreDesc();
}

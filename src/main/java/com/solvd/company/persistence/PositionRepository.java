package com.solvd.company.persistence;

import com.solvd.company.domain.Position;

import java.util.Optional;

public interface PositionRepository {

    void create(Position position);

    Optional<Position> findByName(String name);

}

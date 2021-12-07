package com.solvd.company.service;

import com.solvd.company.domain.Position;

public interface PositionService {

    Position findOrCreate(Position position);

}

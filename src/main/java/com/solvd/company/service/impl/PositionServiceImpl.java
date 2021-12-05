package com.solvd.company.service.impl;

import com.solvd.company.domain.Position;
import com.solvd.company.persistence.PositionRepository;
import com.solvd.company.persistence.jdbcImpl.PositionRepositoryImpl;
import com.solvd.company.persistence.mybatisImpl.PositionMyBatisRepository;
import com.solvd.company.service.PositionService;

public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public PositionServiceImpl() {
        //this.positionRepository = new PositionRepositoryImpl();
        this.positionRepository = new PositionMyBatisRepository();
    }

    @Override
    public Position findOrCreate(Position position) {
        return positionRepository.findByName(position.getName())
                .orElseGet(() -> create(position));
    }

    private Position create(Position position) {
        position.setId(null);
        positionRepository.create(position);
        return position;
    }


}

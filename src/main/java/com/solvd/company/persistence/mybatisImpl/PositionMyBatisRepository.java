package com.solvd.company.persistence.mybatisImpl;

import com.solvd.company.domain.Position;
import com.solvd.company.persistence.MyBatisSessionHolder;
import com.solvd.company.persistence.PositionRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class PositionMyBatisRepository implements PositionRepository {

    @Override
    public void create(Position position) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PositionRepository positionRepository = session.getMapper(PositionRepository.class);
            positionRepository.create(position);
        }
    }

    @Override
    public Optional<Position> findByName(String name) {
        try (SqlSession session = MyBatisSessionHolder.getSqlSessionFactory().openSession(true)) {
            PositionRepository positionRepository = session.getMapper(PositionRepository.class);
            return positionRepository.findByName(name);
        }
    }
}
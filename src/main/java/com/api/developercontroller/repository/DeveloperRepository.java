package com.api.developercontroller.repository;

import com.api.developercontroller.models.Developer;

import java.util.List;

public interface DeveloperRepository {
        void save(Developer dev);

        void update(Developer dev);

        Developer findById(int id);

        Developer deleteById(int id);

        List<Developer> findAll();
}

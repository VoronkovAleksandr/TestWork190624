package ru.voronkov.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voronkov.testwork.model.Car;

import java.util.Collection;

public interface CarRepository extends JpaRepository<Car,Long> {
    Collection<Car> findByOwnerId(Long ownerId);
}

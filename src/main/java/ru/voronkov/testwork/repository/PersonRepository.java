package ru.voronkov.testwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voronkov.testwork.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}

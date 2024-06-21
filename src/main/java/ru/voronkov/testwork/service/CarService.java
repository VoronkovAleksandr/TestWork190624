package ru.voronkov.testwork.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.voronkov.testwork.model.Car;
import ru.voronkov.testwork.model.Person;
import ru.voronkov.testwork.repository.CarRepository;
import ru.voronkov.testwork.repository.PersonRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {

    private CarRepository carRepository;
    private PersonRepository personRepository;
    private int ADULTHOOD = 18;

    public List<Car> findCarByOwnerId(Long ownerId) {
        return (List<Car>) carRepository.findByOwnerId(ownerId);
    }

    public Car findCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.orElse(null);
    }

    public Car addCar(Car car) {
        if (!isValid(car)) return null;
        return carRepository.save(car);
    }

    private boolean isValid(Car car) {

        // Проверяем id
        Car checkingCar = findCarById(car.getId());
        if (checkingCar.equals(car)) return false;

        // Проверка мощности двигателя
        if (car.getHorsepower() <= 0) return false;

        // Проверка владельца
        Person person = personRepository.findById(car.getOwnerId()).orElse(null);
        // Проверка person существует
        if (person == null) return false;
        // Проверка возраст больше 18
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdayDate = person.getBirthdate();
        int age = Period.between(birthdayDate, currentDate).getYears();
        return age >= ADULTHOOD;
    }
}

package ru.voronkov.testwork.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.voronkov.testwork.exception.BadRequestException;
import ru.voronkov.testwork.model.Car;
import ru.voronkov.testwork.model.Person;
import ru.voronkov.testwork.repository.CarRepository;
import ru.voronkov.testwork.repository.PersonRepository;

import java.util.*;

@Service
@Data
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final PersonRepository personRepository;
    private final PersonService personService;
    private final Integer ADULTHOOD = 18;

    public List<Car> findCarByOwnerId(Long ownerId) {
        return (List<Car>) carRepository.findByOwnerId(ownerId);
    }

    public Car findCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.orElse(null);
    }

    public Car addCar(Car car) throws BadRequestException {
        //Проверяем валидность car
        isValid(car);
        return carRepository.save(car);
    }

    private void isValid(Car car) throws BadRequestException {
        if (car.getId() == null) throw new BadRequestException("id не заполнено или не корректно");
        if (car.getModel() == null) throw new BadRequestException("id не заполнено или не корректно");
        if (car.getHorsepower() == null) throw new BadRequestException("id не заполнено или не корректно");
        if (car.getOwnerId() == null) throw new BadRequestException("id не заполнено или не корректно");
        // Проверяем id

        Car checkingCar = findCarById(car.getId());
        if (checkingCar != null) throw new BadRequestException("Car с таким id уже существует");

        // Проверка мощности двигателя
        if (car.getHorsepower() <= 0) throw new BadRequestException("horsepower <= 0");

        // Проверка владельца
        Person owner = personService.findPersonById(car.getOwnerId());
        // Проверка person существует
        if (owner == null) throw new BadRequestException("Person с таким id не существует");
        // Проверка возраст больше 18
        if (personService.getAgePerson(owner) < ADULTHOOD) throw new BadRequestException("Возраст владельца <18");;
    }

    public Long getCarCount() {
        return carRepository.count();
    }

    public Long getUniqueVendorCount() {
        List<Car> cars = carRepository.findAll();
        Set<String> vendors = new HashSet<>();

        for (Car car : cars) {

            String model = car.getModel();
            vendors.add(model.substring(0, model.indexOf('-')));
        }
        return (long) vendors.size();
    }


    public void deleteAllCars() {
        carRepository.deleteAll();
    }
}

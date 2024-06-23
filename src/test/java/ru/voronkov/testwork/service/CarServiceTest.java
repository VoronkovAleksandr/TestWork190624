package ru.voronkov.testwork.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import ru.voronkov.testwork.exception.BadRequestException;
import ru.voronkov.testwork.model.Car;
import ru.voronkov.testwork.model.Person;
import ru.voronkov.testwork.repository.CarRepository;
import ru.voronkov.testwork.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CarServiceTest {



    CarRepository carRepository;

    PersonRepository personRepository;

    CarService carService;

    PersonService personService;

    @BeforeEach
    void setUp() throws BadRequestException {
        this.carRepository = mock(CarRepository.class);
        this.personRepository= mock(PersonRepository.class);
        this.personService = new PersonService(personRepository);
        this.carService = new CarService(carRepository,personRepository,personService);
    }

    @Test
    void findCarByOwnerId() {
        //Вызов метода
        carService.findCarByOwnerId(1L);
        //Проверка
        verify(carRepository).findByOwnerId(1L);
    }

    @Test
    void findCarById() {
        //Вызов метода
        carService.findCarById(1L);
        //Проверка
        verify(carRepository).findById(1L);
    }

    @Test
    void addCar() throws BadRequestException {
        //Предпосылки
        Person testPerson = new Person();
        testPerson.setId(1L);
        testPerson.setName("Test");
        testPerson.setBirthdate(LocalDate.parse("2000-10-10"));

        Car testCar = new Car();
        testCar.setId(1L);
        testCar.setModel("vendor-model");
        testCar.setHorsepower(100);
        testCar.setOwnerId(1L);

        given(personRepository.findById(testPerson.getId())).willReturn(Optional.of(testPerson));

        //Вызов метода
        carService.addCar(testCar);

        //Проверка
        verify(carRepository).save(testCar);
    }

    @Test
    void getCarCount() {
        //Вызов метода
        carService.getCarCount();
        //Проверка
        verify(carRepository).count();
    }

    @Test
    void getUniqueVendorCount() {
        //Предпосылки
        Car testCar = new Car();
        testCar.setId(1L);
        testCar.setModel("vendor-model");
        testCar.setHorsepower(100);
        testCar.setOwnerId(1L);

        given(carRepository.findAll()).willReturn(List.of(testCar));
        //Проверка
        Assertions.assertEquals(carService.getUniqueVendorCount(),1);
    }

    @Test
    void deleteAllCars() {
        //Вызов метода
        carService.deleteAllCars();
        //Проверка
        verify(carRepository).deleteAll();
    }
}
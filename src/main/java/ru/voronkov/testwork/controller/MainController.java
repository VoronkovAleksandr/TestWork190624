package ru.voronkov.testwork.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.voronkov.testwork.dto.PersonWithCar;
import ru.voronkov.testwork.dto.Statistic;
import ru.voronkov.testwork.exception.BadRequestException;
import ru.voronkov.testwork.exception.NotFoundException;
import ru.voronkov.testwork.model.Car;
import ru.voronkov.testwork.model.Person;
import ru.voronkov.testwork.service.CarService;
import ru.voronkov.testwork.service.PersonService;
import ru.voronkov.testwork.service.PersonWithCarService;
import ru.voronkov.testwork.service.StatisticService;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/")
public class MainController {
    private final PersonService personService;
    private final CarService carService;
    private final PersonWithCarService personWithCarService;
    private final StatisticService statisticService;

    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) throws BadRequestException {
        return new ResponseEntity<>(personService.addPerson(person), HttpStatus.CREATED);
    }

    @PostMapping("/car")
    public ResponseEntity<Car> addCar(@RequestBody Car car) throws BadRequestException {
        return new ResponseEntity<>(carService.addCar(car), HttpStatus.CREATED);
    }

    @GetMapping("/personwithcars")
    public ResponseEntity<PersonWithCar> getAllCarsPerson(@RequestParam("personid") Long id) throws NotFoundException {
        return new ResponseEntity<>(personWithCarService.getPersonWithCar(personService.findPersonById(id)), HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<Statistic> getStatistic(){
        return new ResponseEntity<>(statisticService.getStatistic(),HttpStatus.OK);
    }

    @GetMapping("/clear")
    public ResponseEntity<Void> deleteAll(){
        personService.deleteAllPerson();
        carService.deleteAllCars();
        return ResponseEntity.ok().build();
    }

}

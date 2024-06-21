package ru.voronkov.testwork.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.voronkov.testwork.dto.Statistic;
import ru.voronkov.testwork.repository.CarRepository;
import ru.voronkov.testwork.repository.PersonRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticService {
    private CarRepository carRepository;
    private PersonRepository personRepository;
    private PersonService personService;
    private CarService carService;


    public Statistic getStatistic(){
        return new Statistic(personService.getPersonCount(),
                carService.getCarCount(),
                carService.getUniqueVendorCount());
    }
}

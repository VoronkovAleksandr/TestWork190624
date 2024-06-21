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
        Statistic statistic = new Statistic();
        statistic.setPersoncount(personService.getPersonCount());
        statistic.setCarcount(carService.getCarCount());
        statistic.setUniquevendorcount(carService.getUniqueVendorCount());
        return statistic;
    }
}

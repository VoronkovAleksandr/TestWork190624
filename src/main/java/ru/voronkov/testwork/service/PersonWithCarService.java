package ru.voronkov.testwork.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.voronkov.testwork.dto.PersonWithCar;
import ru.voronkov.testwork.model.Person;

@Service
@Data
@AllArgsConstructor
public class PersonWithCarService {

    private CarService carService;

    public PersonWithCar getPersonWithCar(Person person){
        PersonWithCar personWithCar = new PersonWithCar();
        personWithCar.setId(person.getId());
        personWithCar.setName(person.getName());
        personWithCar.setBirthday(person.getBirthdate());
        personWithCar.setCars(carService.findCarByOwnerId(person.getId()));
        return personWithCar;
    }
}

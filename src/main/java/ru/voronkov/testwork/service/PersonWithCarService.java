package ru.voronkov.testwork.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.voronkov.testwork.dto.PersonWithCar;
import ru.voronkov.testwork.exception.NotFoundException;
import ru.voronkov.testwork.model.Person;

@Service
@AllArgsConstructor
public class PersonWithCarService {

    private CarService carService;

    public PersonWithCar getPersonWithCar(Person person) throws NotFoundException {
        PersonWithCar personWithCar = new PersonWithCar();
        isPersonIdValid(person);
        personWithCar.setId(person.getId());
        personWithCar.setName(person.getName());
        personWithCar.setBirthday(person.getBirthdate());
        personWithCar.setCars(carService.findCarByOwnerId(person.getId()));
        return personWithCar;
    }

    private void isPersonIdValid(Person person) throws NotFoundException {
        if (person==null) throw new NotFoundException("Person  с таким id отсутствует");
    }
}

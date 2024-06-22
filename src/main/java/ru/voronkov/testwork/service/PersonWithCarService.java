package ru.voronkov.testwork.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.voronkov.testwork.dto.PersonWithCar;
import ru.voronkov.testwork.exception.BadRequestException;
import ru.voronkov.testwork.exception.NotFoundException;
import ru.voronkov.testwork.model.Person;

@Service
@AllArgsConstructor
public class PersonWithCarService {

    private CarService carService;
    private PersonService personService;

    public PersonWithCar getPersonWithCar(Long id) throws NotFoundException, BadRequestException {
        if (id == null) throw new BadRequestException("id  не может быть null");
        Person person = personService.findPersonById(id);
        isPersonIdValid(person);
        PersonWithCar personWithCar = new PersonWithCar();
        personWithCar.setId(person.getId());
        personWithCar.setName(person.getName());
        personWithCar.setBirthday(person.getBirthdate());
        personWithCar.setCars(carService.findCarByOwnerId(person.getId()));
        return personWithCar;
    }

    private void isPersonIdValid(Person person) throws NotFoundException {
        if (person==null) throw new NotFoundException("Person с таким id отсутствует");
    }
}

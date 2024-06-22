package ru.voronkov.testwork.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.voronkov.testwork.exception.BadRequestException;
import ru.voronkov.testwork.model.Person;
import ru.voronkov.testwork.repository.PersonRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    public Person findPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    public Person addPerson(Person person) throws BadRequestException {
        //Проверяем валидность person
        isValid(person);
        return personRepository.save(person);
    }

    private void isValid(Person person) throws BadRequestException {
        // Проверяем заполненность полей
        if (person.getId() == null) throw new BadRequestException("id не указан или не корректен");
        if (person.getName() == null) throw new BadRequestException("name не указан или не корректен");
        if (person.getBirthdate() == null) throw new BadRequestException("birthday не указан или не корректен");

        Person checkingPerson = findPersonById(person.getId());
        // Проверяем id
        if (checkingPerson != null) throw new BadRequestException("Person с таким id уже существует");
        // Проверяем возраст
        if (getAgePerson(person) <= 0) throw new BadRequestException("Дата рождения Person в будущем");
    }

    public Integer getAgePerson(Person person) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdayDate = person.getBirthdate();
        Integer age = Period.between(birthdayDate, currentDate).getYears();
        return age;
    }

    public void deleteAllPerson() {
        personRepository.deleteAll();
    }

    public Long getPersonCount() {
        return personRepository.count();
    }
}

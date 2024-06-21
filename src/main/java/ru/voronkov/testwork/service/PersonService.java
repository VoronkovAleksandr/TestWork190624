package ru.voronkov.testwork.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
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

    public Person findPersonById(Long id){
        Optional<Person> person = personRepository.findById(id);
        return person.orElse(null);
    }

    public Person addPerson(Person person){
        //Проверяем валидность person
        if (!isValid(person)) return null;
        return personRepository.save(person);
    }

    private boolean isValid(Person person){

        Person checkingPerson = findPersonById(person.getId());

        // Проверяем id
        if (checkingPerson.getId().equals(person.getId())) return false;

        // Проверяем возраст
        return getAgePerson(person)>0;
    }

    public Integer getAgePerson(Person person){
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdayDate = person.getBirthdate();
        return Period.between(birthdayDate, currentDate).getYears();
    }
    public Long getPersonCount(){
        return personRepository.count();
    }
}

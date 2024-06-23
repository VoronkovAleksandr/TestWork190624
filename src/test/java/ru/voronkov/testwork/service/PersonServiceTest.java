package ru.voronkov.testwork.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.voronkov.testwork.exception.BadRequestException;
import ru.voronkov.testwork.model.Person;
import ru.voronkov.testwork.repository.PersonRepository;

import java.time.LocalDate;
import java.time.Period;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PersonServiceTest {

    PersonRepository personRepository;
    PersonService personService;


    @BeforeEach
    void setUp() {
        this.personRepository = mock(PersonRepository.class);
        this.personService = new PersonService(personRepository);
    }

    @Test
    void findPersonById() {
        //Вызов метода
        personService.findPersonById(1L);
        //Проверка
        verify(personRepository).findById(1L);
    }

    @Test
    void addPerson() throws BadRequestException {
        //Предпосылки
        Person testPerson = new Person();
        testPerson.setId(1L);
        testPerson.setName("Test");
        testPerson.setBirthdate(LocalDate.parse("2000-10-10"));

        //Вызов метода
        personService.addPerson(testPerson);

        //Проверка
        verify(personRepository).save(testPerson);
    }

    @Test
    void getAgePerson() {
        //Предпосылки
        Person testPerson = new Person();
        testPerson.setId(1L);
        testPerson.setName("Test");
        testPerson.setBirthdate(LocalDate.parse("2000-10-10"));
        LocalDate currentDate = LocalDate.now();
        LocalDate birthdayDate = testPerson.getBirthdate();
        //Проверка
        Assertions.assertEquals(personService.getAgePerson(testPerson), Period.between(birthdayDate, currentDate).getYears());

    }

    @Test
    void deleteAllPerson() {
        //Вызов метода
        personService.deleteAllPerson();
        //Проверка
        verify(personRepository).deleteAll();
    }

    @Test
    void getPersonCount() {
        //Вызов метода
        personService.getPersonCount();
        //Проверка
        verify(personRepository).count();

    }
}
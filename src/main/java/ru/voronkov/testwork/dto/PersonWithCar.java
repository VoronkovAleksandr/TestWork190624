package ru.voronkov.testwork.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.voronkov.testwork.model.Car;

import java.sql.Date;
import java.util.ArrayList;

@Data
public class PersonWithCar {

    private Long id;

    private String name;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date birthday;

    private ArrayList<Car> cars;
}

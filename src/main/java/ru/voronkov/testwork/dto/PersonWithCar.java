package ru.voronkov.testwork.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import ru.voronkov.testwork.model.Car;

import java.time.LocalDate;
import java.util.List;

@Data
@Component
public class PersonWithCar {

    private Long id;

    private String name;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private List<Car> cars;
}

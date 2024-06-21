package ru.voronkov.testwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Statistic {

    private Long personcount;

    private Long carcount;

    private Long uniquevendorcount;
}

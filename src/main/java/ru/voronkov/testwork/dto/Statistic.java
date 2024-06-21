package ru.voronkov.testwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistic {

    private Long personcount;

    private Long carcount;

    private Long uniquevendorcount;
}

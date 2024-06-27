package demo.application;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExperienceDTO {

    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
}

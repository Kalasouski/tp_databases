package models.sql;

import lombok.*;

@AllArgsConstructor
public class Retiree {
    private final Long id;
    private final String surname;
    private final String name;
    private final String patronymic;
    private final char gender;
    private final String nationality;
    private final int birthYear;
    private final Long phone;
    private final String address;
    private final int retirementExperience;
    private final float retirement;

}

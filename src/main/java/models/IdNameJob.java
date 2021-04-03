package models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IdNameJob {
    private final Long id;
    private final String surname;
    private final String name;
    private final String patronymic;
    private final String jobPosition;
}

package models;

import lombok.*;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class IdNameJob {
    private Long id;
    private String surname;
    private String name;
    private String jobPosition;

    public IdNameJob(Long id){
        this.id = id;
    }
}

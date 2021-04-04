package models.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "job", schema = "public", catalog = "postgres")
public class JobEntity {
    private Long id;
    private String jobPosition;
    private Integer jobPeriod;


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "job_position", nullable = false, length = -1)
    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    @Basic
    @Column(name = "job_period", nullable = true)
    public Integer getJobPeriod() {
        return jobPeriod;
    }

    public void setJobPeriod(Integer jobPeriod) {
        this.jobPeriod = jobPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobEntity jobEntity = (JobEntity) o;

        if (!Objects.equals(id, jobEntity.id)) return false;
        if (!Objects.equals(jobPosition, jobEntity.jobPosition)) return false;
        return Objects.equals(jobPeriod, jobEntity.jobPeriod);
    }


}
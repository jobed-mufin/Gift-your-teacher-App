package com.decagon.rewardyourteacherapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "school")
public class School{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String schoolName;
    private String schoolType;
    private String schoolAddress;
    private String schoolCity;
    private String schoolState;

    public School(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof School)) return false;
        School school = (School) o;
        return getSchoolName().equals(school.getSchoolName()) && getSchoolType().equals(school.getSchoolType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSchoolName(), getSchoolType());
    }
}

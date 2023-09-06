package com.decagon.rewardyourteacherapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="teacher_info")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherExtraInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String yearsOfTeaching;
    private String subjectTaught;
    private String schoolType;
    private String status;
    private String about;
    private String position;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}

package com.decagon.rewardyourteacherapi.payload;

import com.decagon.rewardyourteacherapi.enums.Role;
import com.decagon.rewardyourteacherapi.model.TeacherExtraInfo;
import com.decagon.rewardyourteacherapi.model.User;
import lombok.*;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String schoolName;
    private String name;
    private String email;
    private String password;

    private  String phoneNumber;
    private String imageUrl;
    private BigDecimal wallet;
    private String token;
    private Role role;
    private String yearsOfTeaching;
    private String subjectTaught;
    private String schoolType;
    private String status;
    private String about;
    private String position;


    public UserDTO(Long id, String name, String imageUrl, BigDecimal wallet) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.wallet = wallet;

    }


    public UserDTO(Long id, String name, String imageUrl, BigDecimal wallet, String token, Role role) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.wallet = wallet;
        this.token = token;
        this.role = role;
    }


    public UserDTO(long id, String name, String profileImage, BigDecimal wallet, String email, String schoolName, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.imageUrl = profileImage;
        this.wallet = wallet;
        this.email = email;
        this.schoolName = schoolName;
        this.phoneNumber = phoneNumber;
    }

    public UserDTO(String name, String email, String password) {
        this.name=name;
        this.email=email;
        this.password=password;
    }

    public UserDTO(User e, TeacherExtraInfo teacherExtraInfoByUser) {
        System.out.println(teacherExtraInfoByUser);
        System.out.println(e.getSchool().getSchoolName());
        this.id  = e.getId();
        this.name = e.getName();
        this.position = teacherExtraInfoByUser.getPosition();
        this.yearsOfTeaching = teacherExtraInfoByUser.getYearsOfTeaching();
        this.schoolName = e.getSchool().getSchoolName();
    }
}

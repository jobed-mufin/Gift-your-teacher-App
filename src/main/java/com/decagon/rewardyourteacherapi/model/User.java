package com.decagon.rewardyourteacherapi.model;


import com.decagon.rewardyourteacherapi.enums.Role;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,name = "name")
    private String name;
    @Column(nullable = false, unique = true,name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String password;
    private String phoneNumber;
    private String profileImage;
    private BigDecimal wallet = new BigDecimal(0.0);
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToOne
    @JoinColumn(name = "school_id")
//    @Column(nullable = false)
    private School school;

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public User(long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(long id) {
        this.id = id;
    }
    public User(long id, String name, String profileImage) {
        this.id = id;
        this.name = name;
        this.profileImage = profileImage;
    }

    public User( String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String email) {
        this.email = email;
    }

}

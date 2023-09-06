package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.enums.Role;
import com.decagon.rewardyourteacherapi.model.Notification;
import com.decagon.rewardyourteacherapi.model.User;
import com.decagon.rewardyourteacherapi.payload.LoginDTO;
import org.springframework.data.domain.Page;
import com.decagon.rewardyourteacherapi.payload.UserDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.security.Principal;

@Service
public interface UserService {

    UserDTO login(LoginDTO loginDto);

    Object signUpUser(UserDTO userDTO, Role role);

    String authenticateOauth2User(UserDTO request);

    Page<UserDTO> getSchoolTeachers(Long id, int page, int size);

    UserDTO updateUserProfile(UserDTO request);

    BigDecimal getCurrentWalletBalance();



    Page<UserDTO> retrieveTeachers(int page, int size);

    UserDTO viewTeacherProfile(Long id);

    UserDTO viewStudentProfile(Long id);

    Notification teacherAppreciatesStudent(Long userId);

    List<UserDTO> searchTeacher(String name);

}



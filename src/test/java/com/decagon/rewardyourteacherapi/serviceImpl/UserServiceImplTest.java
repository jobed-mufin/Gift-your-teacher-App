//package com.decagon.rewardyourteacherapi.serviceImpl;
//
//import com.decagon.rewardyourteacherapi.RewardYourTeacherApiApplication;
//import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
//import com.decagon.rewardyourteacherapi.model.User;
//import com.decagon.rewardyourteacherapi.payload.UserDTO;
//import com.decagon.rewardyourteacherapi.repository.NotificationRepository;
//import com.decagon.rewardyourteacherapi.repository.UserRepository;
//import com.decagon.rewardyourteacherapi.security.JwtService;
//import com.decagon.rewardyourteacherapi.service.UserService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.decagon.rewardyourteacherapi.enums.Role.TEACHER;
//import static org.junit.jupiter.api.Assertions.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = RewardYourTeacherApiApplication.class)
//
//class UserServiceImplTest {
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserRepository userRepository;
//
//    @MockBean
//    AuthenticationManager authenticationManager;
//    @MockBean
//    JwtService jwtService;
//    @MockBean
//    PasswordEncoder passwordEncoder;
//    @MockBean
//    NotificationRepository notificationRepository;
//
//
//    @MockBean
//    SecurityContextHolder securityContextHolder;
//
//    User user2 = new User( "george", "test2@gamil.com", "password", TEACHER);
//
//
//
//
////    @Test
////    void updateUserProfile(){
////        userService = new UserServiceImpl( authenticationManager, userRepository, walletRepository, passwordEncoder);
////        User uUser = new User("george", "victim","test@yahoo.com",passwordEncoder.encode("this"),  Role.STUDENT);
////        userRepository.save(uUser);
////        UserDTO modified = new UserDTO("King george", "Duren", "newimage.png");
////        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
////                .thenReturn(new org.springframework.security.core.userdetails.User(uUser.getEmail(), uUser.getPassword(), new ArrayList<SimpleGrantedAuthority>()));
////        Assertions.assertEquals("King george", userService.updateUserProfile(modified, 1L).getFirstname());
////    }
//
//
//    @Test
//    void SignUpUser(){
//        userService =  new UserServiceImpl(authenticationManager, userRepository, passwordEncoder, notificationRepository);
//        user2.setId(2L);
//        Assertions.assertEquals(PayloadToModel.mapUserToDTO(user2).getName(), userService.signUpUser(user2).getName());
//        Exception exception = assertThrows(RuntimeException.class, () -> userService.signUpUser(user2));
//
//        String expectedMessage = "Email test2@gamil.com has been taken";
//        String actualMessage = exception.getMessage();
//
//        assertTrue(actualMessage.contains(expectedMessage));
//
//    }
//    @Test
//    void getSchoolTeachers(){
//        Pageable pageable = PageRequest.of(0, 5);
//        List<UserDTO> list= new ArrayList<>();
//        Page<UserDTO> page = new PageImpl<>(list, pageable, 0);
//        userService =  new UserServiceImpl(authenticationManager, userRepository, passwordEncoder, notificationRepository);
//        Assertions.assertEquals(page, userService.getSchoolTeachers(1L, 0,5));
//    }
//
//    @Test
//    void searchTeacher() {
//        List<UserDTO>userList = new ArrayList<>();
//        userService = new UserServiceImpl( authenticationManager ,userRepository, passwordEncoder, notificationRepository);
//        Assertions.assertEquals(userList, userService.searchTeacher("bukky"));
//    }
//
//}

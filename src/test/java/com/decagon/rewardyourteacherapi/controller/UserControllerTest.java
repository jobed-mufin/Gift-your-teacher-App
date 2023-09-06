//package com.decagon.rewardyourteacherapi.controller;
//
//import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
//import com.decagon.rewardyourteacherapi.enums.Role;
//import com.decagon.rewardyourteacherapi.model.User;
//import com.decagon.rewardyourteacherapi.payload.LoginDTO;
//import com.decagon.rewardyourteacherapi.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.*;
//
//@ContextConfiguration(classes = {UserController.class})
//@ExtendWith(SpringExtension.class)
//class UserControllerTest {
//    @Autowired
//    private UserController userController;
//
//    @MockBean
//    private UserService userService;
//
//
//    @Test
//    void testLogin() throws Exception {
//        when(userService.login(any())).thenReturn("token");
//
//        LoginDTO loginDto = new LoginDTO();
//        loginDto.setEmail("global@gmail.com");
//        loginDto.setPassword("1234");
//        String content = (new ObjectMapper()).writeValueAsString(loginDto);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/user/register/teacher")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
//    }
//
//    @Test
//    void testSigUpUser() throws Exception {
//        User user = new User();
//        user.setRole(Role.TEACHER);
//        user.setPassword("pass");
//        user.setName("george");
////        user.setLastName("king");
//        user.setEmail("test@gamil.com");
//        String content = (new ObjectMapper()).writeValueAsString(user);
//        when(userService.signUpUser(user)).thenReturn(PayloadToModel.mapUserToDTO(user));
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/user/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
//    }
//
//}

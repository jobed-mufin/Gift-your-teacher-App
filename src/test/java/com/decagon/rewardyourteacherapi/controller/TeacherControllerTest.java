//package com.decagon.rewardyourteacherapi.controller;
//
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.when;
//
//import com.decagon.rewardyourteacherapi.enums.Role;
//import com.decagon.rewardyourteacherapi.mapper.PayloadToModel;
//import com.decagon.rewardyourteacherapi.model.School;
//import com.decagon.rewardyourteacherapi.model.User;
//import com.decagon.rewardyourteacherapi.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ContextConfiguration(classes = {TeacherController.class})
//@ExtendWith(SpringExtension.class)
//class TeacherControllerTest {
//    @Autowired
//    private TeacherController teacherController;
//
//    @MockBean
//    private UserService userService;
//
//    /**
//     * Method under test: {@link TeacherController#viewTeacherById(Long)}
//     */
//    @Test
//    void testViewTeacherById() throws Exception {
//        School school = new School();
//        school.setId(123L);
//        school.setSchoolAddress("42 Main St");
//        school.setSchoolCity("School City");
//        school.setSchoolName("School Name");
//        school.setSchoolState("School State");
//        school.setSchoolType("School Type");
//
//        User user = new User();
//        user.setEmail("jane.doe@example.org");
//        user.setFirstName("Jane");
//        user.setId(123L);
//        user.setLastName("Doe");
//        user.setPassword("iloveyou");
//        user.setProfileImage("Profile Image");
//        user.setRole(Role.STUDENT);
//        user.setSchool(school);
//        when(userService.viewTeacherProfile(anyLong())).thenReturn(PayloadToModel.MapUserToDTO(user));
//        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/viewSingleTeacherById");
//        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));
//        MockMvcBuilders.standaloneSetup(teacherController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"message\":\"Request Successful\",\"success\":true,\"payload\":{\"id\":123,\"firstName\":\"Jane\",\"lastName\":\"Doe"
//                                        + "\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"profileImage\":\"Profile Image\",\"role\":\"STUDENT"
//                                        + "\",\"school\":{\"id\":123,\"schoolName\":\"School Name\",\"schoolType\":\"School Type\",\"schoolAddress\":\"42 Main"
//                                        + " St\",\"schoolCity\":\"School City\",\"schoolState\":\"School State\"}}}"));
//    }
//}
//

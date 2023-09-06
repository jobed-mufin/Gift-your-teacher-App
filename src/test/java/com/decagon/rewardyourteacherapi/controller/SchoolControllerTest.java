package com.decagon.rewardyourteacherapi.controller;

import com.decagon.rewardyourteacherapi.payload.UserDTO;
import com.decagon.rewardyourteacherapi.service.SchoolService;
import com.decagon.rewardyourteacherapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SchoolController.class})
class SchoolControllerTest {

    @MockBean
    UserService userService;

    @MockBean
    SchoolService schoolService;

    @Autowired
    SchoolController schoolController;

    @Test
    void getTeachers() throws Exception {
        Pageable pageable = PageRequest.of(0, 5);
        List<UserDTO> list= new ArrayList<>();
        Page<UserDTO> page = new PageImpl<>(list, pageable, 0);
        when(userService.getSchoolTeachers(anyLong(), anyInt(), anyInt())).thenReturn(page);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/school/1/1&10");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(schoolController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
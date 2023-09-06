package com.decagon.rewardyourteacherapi.serviceImpl;

import com.decagon.rewardyourteacherapi.RewardYourTeacherApiApplication;
import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.repository.SchoolRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RewardYourTeacherApiApplication.class)
public class SchoolServiceImplTest {
    @Autowired
    private SchoolServiceImpl schoolService;

    @MockBean
    private SchoolRepository schoolRepository;

    @Test
    public void getAllSchool(){
        School school = new School();
        school.setId(1);
        school.setSchoolName("British International School Lagos");
        school.setSchoolAddress("1, Landbridge Avenue, Oniru Private Estate, Victoria Island, Lagos Nigeria");
        school.setSchoolCity("ikeja");
        school.setSchoolState("Lagos");
        school.setSchoolType("secondary");

        School school1 = new School();
        school1.setId(2);
        school1.setSchoolName("Brookstone Secondary School");
        school1.setSchoolAddress("International Airport Road, Igwuruta, Port Harcourt, Rivers Nigeria");
        school1.setSchoolCity("Igwuruta");
        school1.setSchoolState("Port Harcourt");
        school1.setSchoolType("secondary");

        List<School> schoolList = new ArrayList<>();

        schoolList.add(school);
        schoolList.add(school1);

        Page<School> page = new PageImpl<>(schoolList);

        Mockito.when(schoolRepository.findAll(PageRequest.of(0,10))).thenReturn(page);

        Page<School> schools = schoolService.retrieveSchools(0, 10);

        Assertions.assertEquals(page,schools);

    }

}

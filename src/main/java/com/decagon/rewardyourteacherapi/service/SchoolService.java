package com.decagon.rewardyourteacherapi.service;

import com.decagon.rewardyourteacherapi.model.School;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SchoolService {
    int getSchools();
    List<School> saveSchools(List<School> school);

    Page<School> retrieveSchools(int page, int size);

    public List<School> retrieve();

}

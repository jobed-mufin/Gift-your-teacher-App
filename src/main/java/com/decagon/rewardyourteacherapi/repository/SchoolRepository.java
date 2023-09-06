package com.decagon.rewardyourteacherapi.repository;

import com.decagon.rewardyourteacherapi.model.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    School findSchoolBySchoolName(String name);

}

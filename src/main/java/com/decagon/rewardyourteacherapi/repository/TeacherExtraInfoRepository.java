package com.decagon.rewardyourteacherapi.repository;

import com.decagon.rewardyourteacherapi.model.TeacherExtraInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import com.decagon.rewardyourteacherapi.model.User;

public interface TeacherExtraInfoRepository extends JpaRepository<TeacherExtraInfo,Long> {
    TeacherExtraInfo getTeacherExtraInfoByUser(User user);


}

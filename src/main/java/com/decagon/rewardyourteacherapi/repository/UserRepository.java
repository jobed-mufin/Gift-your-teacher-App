package com.decagon.rewardyourteacherapi.repository;

import com.decagon.rewardyourteacherapi.enums.Role;
import com.decagon.rewardyourteacherapi.model.School;
import com.decagon.rewardyourteacherapi.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByIdAndRole(String id, String role);
    Page<User> findUsersByRole(Pageable pageable, Role role);


    List<User> findUsersByRoleAndNameContainingIgnoreCase(
            Role role, String name);

    Page<User> findAllBySchoolAndRole(School school, Role role, Pageable pageable);

    Optional<User> findUserByEmailAndRole(String email, Role role);

    Optional<User> findUserByIdAndRole (Long id , Role role);

    //User findTransactionBySender(Long id, Role role);
//    Optional<User> findUserByFirstNameAndPhoneNumberAndRole(String firstName, String phoneNumber, Role role);


}

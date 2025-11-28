package com.boseprofessional.aadhaarService.repository;

import com.boseprofessional.aadhaarService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByAadhaarId(String aadhaarId);
}

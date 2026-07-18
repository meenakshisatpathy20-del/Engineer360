package com.engineer360.profile;

import com.engineer360.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeveloperProfileRepository
        extends JpaRepository<DeveloperProfile, Long> {

    Optional<DeveloperProfile> findByUser(User user);
}
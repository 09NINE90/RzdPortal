package com.example.rzd.repository;

import com.example.rzd.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);

    Optional<User> findById(Long id);

    @Query(value = "SELECT * FROM users su WHERE roles = :role AND creatorId =:creatorId ORDER BY su.id DESC", nativeQuery = true)
    List<User> findUserByRoles(@Param("role") String role, @Param("creatorId") Long creatorId);

}

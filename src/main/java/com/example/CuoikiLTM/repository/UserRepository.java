package com.example.CuoikiLTM.repository;



import com.example.CuoikiLTM.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    Optional<User> findUserByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);


    boolean existsByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.username = :newUsername, u.email = :newEmail WHERE u.id = :userId")
    void updateUser(@Param("userId") Long userId, @Param("newUsername") String newUsername, @Param("newEmail") String newEmail);
    @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
    @Query(value = "SELECT * FROM user ORDER BY user.chuoithang DESC LIMIT 10", nativeQuery = true)
    List<User> getTop10UsersByWinningStreak();
    @Query(value = "SELECT * FROM user ORDER BY user.chuoithua DESC LIMIT 10", nativeQuery = true)
    List<User> getTop10UsersByLossningStreak();
    @Query(value = "SELECT * FROM user ORDER BY user.sotranthua DESC LIMIT 10", nativeQuery = true)
    List<User> getTop10UsersSoTranThua();
    @Query(value = "SELECT * FROM user ORDER BY user.sotranthang DESC LIMIT 10", nativeQuery = true)
    List<User> getTop10UsersBySoTranThang();
}
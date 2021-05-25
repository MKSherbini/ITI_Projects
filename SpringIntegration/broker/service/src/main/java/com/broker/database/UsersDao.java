//package com.broker.database;
//
//import com.broker.model.SystemUser;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Collection;
//
//public interface UsersDao extends CrudRepository<SystemUser, String> {
////    Long count();
//
//    Long countByEnabled(int enabled);
//
//    SystemUser findByUsername(String username);
//
//    @Transactional
//    @Modifying
//    @Query(value = "insert into users VALUES (:username,:password,:enabled)", nativeQuery = true)
//    void addUser(@Param("username") String username, @Param("password") String password, @Param("enabled") boolean enabled);
//
//    @Transactional
//    @Modifying
//    @Query(value = "update SystemUser u set u.password=?2, u.enabled=?3 where u.username=?1")
//    void updateUser(String username, String password, int enabled);
//
//
//    SystemUser queryDistinctFirstByUsernameEqualsOrUsernameIsInOrUsernameLike(String usernameEquals, Collection<String> usernameIsIn, String usernameLike);
//
////    List<SystemUser> getUsers();
//
////    void deleteUser(String username);
//
//}

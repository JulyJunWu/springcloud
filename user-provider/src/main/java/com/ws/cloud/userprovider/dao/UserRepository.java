package com.ws.cloud.userprovider.dao;

import com.ws.cloud.userprovider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jun
 * data  2019-10-14 23:52
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIdAndName(Long id, String name);
}

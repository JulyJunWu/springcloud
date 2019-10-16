package provider.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import provider.entity.User;

/**
 * @author Jun
 * data  2019-10-14 23:52
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

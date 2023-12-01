package wild.protection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import wild.protection.models.Admin;

import java.util.Optional;
@Repository
public interface UserRepository  extends JpaRepository<Admin, Long>  {

	Optional<Admin> findByUsername(String username);
    
}

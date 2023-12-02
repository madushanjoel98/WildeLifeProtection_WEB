package wild.protection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wild.protection.models.*;
import java.util.List;
import java.util.Optional;



public interface PublicLoginRepository extends JpaRepository<PublicLogin, Long> {
	Optional<PublicLogin> findByEmail(String email);
}

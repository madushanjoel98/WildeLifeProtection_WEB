package wild.protection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wild.protection.models.*;

public interface PublicComplainRepository extends JpaRepository<PublicComplain, Long> {
}

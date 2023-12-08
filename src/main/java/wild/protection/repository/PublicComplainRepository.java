package wild.protection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import wild.protection.models.*;
import java.util.List;
import java.util.Optional;


public interface PublicComplainRepository extends JpaRepository<PublicComplain, Long> {

//	@Query("SELECT pc FROM PublicComplain pc WHERE pc.publicid.id = ?1")
//    Optional<PublicComplain> findByPublicid(Long publicId);
	
	List<PublicComplain> findByPublicid(PublicLogin publicid);
}

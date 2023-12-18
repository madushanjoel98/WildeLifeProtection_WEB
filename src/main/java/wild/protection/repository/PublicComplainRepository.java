package wild.protection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import wild.protection.models.*;
import java.util.List;
import java.util.Optional;


public interface PublicComplainRepository extends JpaRepository<PublicComplain, Long> {

	@Query("SELECT pc FROM PublicComplain pc WHERE pc.countries.id = ?1")
	List<PublicComplain> findByCountry(int countyID);
	
	List<PublicComplain> findByPublicid(PublicLogin publicid);
	
	@Query("SELECT pc FROM PublicComplain pc WHERE pc.review_status= 0")
	List<PublicComplain> findPenddings();
	
	@Query("SELECT pc FROM PublicComplain pc WHERE pc.countries.id = ?1 AND pc.review_status= 0")
	List<PublicComplain> findByPendingsbyCountry(int countyID);
	

}

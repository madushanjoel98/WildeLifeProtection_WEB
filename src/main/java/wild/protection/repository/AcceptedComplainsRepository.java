package wild.protection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wild.protection.models.AcceptedComplains;
import wild.protection.models.PublicComplain;

import java.util.List;




public interface AcceptedComplainsRepository extends JpaRepository<AcceptedComplains, Long> {
	List<AcceptedComplains> findByComplain(PublicComplain complain);
}

package wild.protection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wild.protection.models.*;
import java.util.List;



public interface RejectResonsRepository extends JpaRepository<RejectResons, Long> {
	List<RejectResons> findByComplaintid(PublicComplain complaintid);
}

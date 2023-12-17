package wild.protection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wild.protection.models.*;
import java.util.List;
import wild.protection.models.PublicComplain;




public interface RejectResonsRepository extends JpaRepository<RejectResons, Long> {
	List<RejectResons> findByComplaintid(PublicComplain complaintid);
	void deleteByComplaintid(PublicComplain complaintid);
}

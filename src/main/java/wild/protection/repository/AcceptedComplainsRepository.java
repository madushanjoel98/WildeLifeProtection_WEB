package wild.protection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wild.protection.models.AcceptedComplains;



public interface AcceptedComplainsRepository extends JpaRepository<AcceptedComplains, Long> {
}

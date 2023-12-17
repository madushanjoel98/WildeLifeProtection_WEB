package wild.protection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wild.protection.models.AcceptedComplains;
import wild.protection.models.AdminTypes;

public interface AdminTypeRespos extends JpaRepository<AdminTypes, Integer>{

//INSERT INTO `admin_types` (`name`) VALUES
//('Master'),('Minor');

	
	
}

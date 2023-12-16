package wild.protection.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wild.protection.controllers.admins.LoginController;
import wild.protection.models.AdminTypes;
import wild.protection.repository.AdminTypeRespos;

@Service
public class AdminAddService {
@Autowired
AdminTypeRespos adminTypeRespos;
Logger logger = LoggerFactory.getLogger(AdminAddService.class);
public void createWhenTableisEmpty() {
	if(adminTypeRespos.count()==0) {
		logger.info("Creating Admin Types");
		AdminTypes type1=new AdminTypes();
		type1.setName("Master");
	    adminTypeRespos.save(type1);
		AdminTypes type2=new AdminTypes();
		type2.setName("Minor");
		   adminTypeRespos.save(type2);
		   AdminTypes type3=new AdminTypes();
			type2.setName("Super");
			   adminTypeRespos.save(type2);
			   
	}
}

}

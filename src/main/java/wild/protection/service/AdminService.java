package wild.protection.service;

import org.springframework.stereotype.Service;

import wild.protection.dto.request.ChangePassword;
import wild.protection.models.Admin;
import wild.protection.utils.AdminExpections;

@Service
public interface AdminService {
 public void changePassword(ChangePassword changepass,Admin loggedAdmin) throws AdminExpections;
}

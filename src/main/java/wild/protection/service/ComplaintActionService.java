package wild.protection.service;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.springframework.stereotype.Service;

import wild.protection.dto.StatusDTO;
import wild.protection.models.AcceptedComplains;
import wild.protection.models.Admin;
import wild.protection.models.PublicComplain;
import wild.protection.models.RejectResons;
import wild.protection.utils.AdminExpections;

@Service
public interface ComplaintActionService {

	public void acceptChange(AcceptedComplains acceptAction,Admin admin,long publicComplainid) throws AdminExpections;
	public void rejectChange(RejectResons rejectResons,Admin admin,long publicComplainid) throws AdminExpections;
    public StatusDTO getStatus(long compid)throws AdminExpections;
}

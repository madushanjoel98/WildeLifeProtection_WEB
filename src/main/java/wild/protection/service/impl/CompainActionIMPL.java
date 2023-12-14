package wild.protection.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wild.protection.dto.StatusDTO;
import wild.protection.models.AcceptedComplains;
import wild.protection.models.Admin;
import wild.protection.models.PublicComplain;
import wild.protection.models.RejectResons;
import wild.protection.repository.AcceptedComplainsRepository;
import wild.protection.repository.PublicComplainRepository;
import wild.protection.repository.RejectResonsRepository;
import wild.protection.service.ComplainService;
import wild.protection.service.ComplaintActionService;
import wild.protection.utils.AdminExpections;
import wild.protection.utils.UserContextUsage;

@Service
@Transactional
public class CompainActionIMPL implements ComplaintActionService {
	private final int ACCEPT = 1, REJECT = 2, PENDDING = 0;

	@Autowired
	ComplainService complainService;

	@Autowired
	PublicComplainRepository complainRepository;

	@Autowired
	UserContextUsage contextUsage;

	@Autowired
	AcceptedComplainsRepository acceptedComplainsRepository;

	@Autowired
	RejectResonsRepository rejectResonsRepository;

	@Override
	public void acceptChange(AcceptedComplains acceptAction, Admin admin, long publicComplainid)
			throws AdminExpections {
		try {
			PublicComplain complain = complainRepository.findById(publicComplainid)
					.orElseThrow(() -> new EntityNotFoundException("Complain not Found"));
			if (complain.getReview_status() != 0) {
				throw new AdminExpections("Action Already getted");

			}
			acceptAction.setAdmin(admin);
			acceptAction.setComplain(complain);
			acceptAction.setComplaintDate(new Date());
			acceptedComplainsRepository.save(acceptAction);
			complain.setReview_status(ACCEPT);

		} catch (Exception e) {
			throw new AdminExpections("Accept Fail:" + e.getMessage());
		}

	}

	@Override
	public void rejectChange(RejectResons rejectResons, Admin admin, long publicComplainid) throws AdminExpections {
		try {

			PublicComplain complain = complainRepository.findById(publicComplainid)
					.orElseThrow(() -> new EntityNotFoundException("Complain not Found"));
			if (complain.getReview_status() != 0) {
				throw new AdminExpections("Action Already getted");

			}
			rejectResons.setAdmin(admin);
			rejectResons.setComplaintid(complain);
			rejectResons.setRejectDate(new Date());
			rejectResonsRepository.save(rejectResons);
			complain.setReview_status(REJECT);

		} catch (Exception e) {
			throw new AdminExpections("Fail to Reject:" + e.getMessage());
		}

	}

	@Override
	public StatusDTO getStatus(long compid) throws AdminExpections {
		//This for Public
		StatusDTO output=new StatusDTO();
		
		try {
			PublicComplain complain = complainRepository.findById(compid)
					.orElseThrow(() -> new EntityNotFoundException("Complain not Found"));
			output.setReview_statusCode(complain.getReview_status());
			if(complain.getReview_status()==ACCEPT) {
				
				AcceptedComplains acomps=acceptedComplainsRepository.findByComplain(complain).getFirst();
				output.setAccepted(acomps);
				return output;
			} else
			if(complain.getReview_status()==REJECT) {
				RejectResons rej =rejectResonsRepository.findByComplaintid(complain).getFirst();
				output.setRejected(rej);
				return output;
			} 
			
			
			
		} catch (Exception e) {
			throw new AdminExpections("Fail to get Status:" + e.getMessage());
		}
		return output;
	}

}

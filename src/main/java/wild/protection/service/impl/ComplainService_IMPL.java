package wild.protection.service.impl;

import org.springframework.stereotype.Service;
import wild.protection.models.PublicComplain;
import wild.protection.service.ComplainService;
import wild.protection.utils.AdminExpections;

import java.util.List;

@Service
public class ComplainService_IMPL implements ComplainService {


    @Override
    public List<PublicComplain> getPublicComplainbyCountry(int id) throws AdminExpections {
        return null;
    }

    @Override
    public List<PublicComplain> getALLCompains() throws AdminExpections {
        return null;
    }
}

package wild.protection.service;

import org.springframework.stereotype.Service;
import wild.protection.models.PublicComplain;
import wild.protection.utils.AdminExpections;

import java.util.List;

@Service
public interface ComplainService {
    List<PublicComplain> getPublicComplainbyCountry(int id) throws AdminExpections;
    List<PublicComplain> getALLCompains() throws AdminExpections;
    public void update(long complainId,PublicComplain newComplain) throws Exception;
}

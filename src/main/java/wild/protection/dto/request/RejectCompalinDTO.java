package wild.protection.dto.request;

import javax.validation.constraints.NotEmpty;

import wild.protection.models.AcceptedComplains;
import wild.protection.models.RejectResons;

public class RejectCompalinDTO {
@NotEmpty(message = "ID needed")
private long cID;

@NotEmpty(message = "reject Resons needed")
private RejectResons rejectResons;
public long getcID() {
	return cID;
}
public void setcID(long cID) {
	this.cID = cID;
}
public RejectResons getRejectResons() {
	return rejectResons;
}
public void setRejectResons(RejectResons rejectResons) {
	this.rejectResons = rejectResons;
}





}

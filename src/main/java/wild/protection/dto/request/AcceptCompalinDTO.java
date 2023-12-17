package wild.protection.dto.request;

import wild.protection.models.AcceptedComplains;

public class AcceptCompalinDTO {
private long cID;
private AcceptedComplains acceptcom;


public long getcID() {
	return cID;
}
public void setcID(long cID) {
	this.cID = cID;
}
public AcceptedComplains getAcceptcom() {
	return acceptcom;
}
public void setAcceptcom(AcceptedComplains acceptcom) {
	this.acceptcom = acceptcom;
}


}

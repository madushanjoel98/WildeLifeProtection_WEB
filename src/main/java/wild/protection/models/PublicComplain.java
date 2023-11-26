package wild.protection.models;

import javax.persistence.*;
import java.util.Set;


@Entity
public class PublicComplain {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long pcompId;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(length = 45)
    private String mobileNumber;

    @Column(nullable = false)
    private Integer disrictid;

    @Column(nullable = false, length = 45)
    private String locationDetails;

    @Column(nullable = false, length = 45)
    private String complain;

    @OneToMany(mappedBy = "complain")
    private Set<AcceptedComplains> complainAcceptedComplainses;

    @OneToMany(mappedBy = "complain")
    private Set<Medias> complainMediases;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicid_id", nullable = false)
    private PublicLogin publicid;

    @OneToMany(mappedBy = "complaintid")
    private Set<RejectResons> complaintidRejectResonses;

    public Long getPcompId() {
        return pcompId;
    }

    public void setPcompId(final Long pcompId) {
        this.pcompId = pcompId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Integer getDisrictid() {
        return disrictid;
    }

    public void setDisrictid(final Integer disrictid) {
        this.disrictid = disrictid;
    }

    public String getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(final String locationDetails) {
        this.locationDetails = locationDetails;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(final String complain) {
        this.complain = complain;
    }

    public Set<AcceptedComplains> getComplainAcceptedComplainses() {
        return complainAcceptedComplainses;
    }

    public void setComplainAcceptedComplainses(
            final Set<AcceptedComplains> complainAcceptedComplainses) {
        this.complainAcceptedComplainses = complainAcceptedComplainses;
    }

    public Set<Medias> getComplainMediases() {
        return complainMediases;
    }

    public void setComplainMediases(final Set<Medias> complainMediases) {
        this.complainMediases = complainMediases;
    }

    public PublicLogin getPublicid() {
        return publicid;
    }

    public void setPublicid(final PublicLogin publicid) {
        this.publicid = publicid;
    }

    public Set<RejectResons> getComplaintidRejectResonses() {
        return complaintidRejectResonses;
    }

    public void setComplaintidRejectResonses(final Set<RejectResons> complaintidRejectResonses) {
        this.complaintidRejectResonses = complaintidRejectResonses;
    }

}

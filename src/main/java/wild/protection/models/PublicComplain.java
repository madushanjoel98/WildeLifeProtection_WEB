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

   
   
    @Column(nullable = false, length = 100)
    private String locationDetails;

    @Column(nullable = false, length = 900)
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

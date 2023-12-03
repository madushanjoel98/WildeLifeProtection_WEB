package wild.protection.models;

import javax.persistence.*;


@Entity
public class RejectResons {

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
    private Long idrejectResons;

    @Column(nullable = false, columnDefinition = "text")
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaintid_id", nullable = false)
    private PublicComplain complaintid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    public Long getIdrejectResons() {
        return idrejectResons;
    }

    public void setIdrejectResons(final Long idrejectResons) {
        this.idrejectResons = idrejectResons;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }

    public PublicComplain getComplaintid() {
        return complaintid;
    }

    public void setComplaintid(final PublicComplain complaintid) {
        this.complaintid = complaintid;
    }

}

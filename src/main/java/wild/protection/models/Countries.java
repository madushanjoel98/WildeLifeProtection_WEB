package wild.protection.models;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Countries {

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
    private Integer id;

    @Column(nullable = false, length = 2)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "countryid")
    private Set<Admin> countryidAdmins;

    @OneToMany(mappedBy = "countryid")
    private Set<PublicLogin> countryidPublicLogins;

    @OneToMany(mappedBy = "pcompId")
    private Set<PublicComplain> publicComplain;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Admin> getCountryidAdmins() {
        return countryidAdmins;
    }

    public void setCountryidAdmins(final Set<Admin> countryidAdmins) {
        this.countryidAdmins = countryidAdmins;
    }

    public Set<PublicLogin> getCountryidPublicLogins() {
        return countryidPublicLogins;
    }

    public void setCountryidPublicLogins(final Set<PublicLogin> countryidPublicLogins) {
        this.countryidPublicLogins = countryidPublicLogins;
    }

}

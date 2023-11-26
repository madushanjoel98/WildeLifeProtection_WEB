package wild.protection.models;

import javax.persistence.*;
import java.util.Set;

//ok
@Entity
public class Admin {

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
    private Long userId;

    @Column
    private Boolean accountNonLocked;

    @Column
    private String password;

    @Column
    private String username;

    @ManyToMany(mappedBy = "accessforAdmins")
    private Set<Accesslevel> accessforAccesslevels;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryid_id")
    private Countries countryid;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(final Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Set<Accesslevel> getAccessforAccesslevels() {
        return accessforAccesslevels;
    }

    public void setAccessforAccesslevels(final Set<Accesslevel> accessforAccesslevels) {
        this.accessforAccesslevels = accessforAccesslevels;
    }

    public Countries getCountryid() {
        return countryid;
    }

    public void setCountryid(final Countries countryid) {
        this.countryid = countryid;
    }

}

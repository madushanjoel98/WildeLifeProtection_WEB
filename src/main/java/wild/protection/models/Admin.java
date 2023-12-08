package wild.protection.models;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.List;
import java.util.Set;

//ok
@Entity
@Table(name = "admin")
public class Admin implements UserDetails {

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
    
    @Column
    private String role;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "accessforAdmins")
    private Set<Accesslevel> accessforAccesslevels;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryid_id")
    private Countries countryid;

    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    private Set<AcceptedComplains> acceptComplain;
    
    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    private Set<RejectResons> rejectComplain;

	public Set<AcceptedComplains> getAcceptComplain() {
		return acceptComplain;
	}

	public void setAcceptComplain(Set<AcceptedComplains> acceptComplain) {
		this.acceptComplain = acceptComplain;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
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

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return List.of(() -> "read");
    }

    @Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

}

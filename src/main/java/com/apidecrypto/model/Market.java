package com.apidecrypto.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Maximiliano Almiron
 *
 */
@EqualsAndHashCode
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"code"})})
public class Market {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @Column(name = "code", unique = true)
    @NotNull(message = "The code cannot be null")
    private String code;
    
    @Column(name = "description")
    @NotNull(message = "The description cannot be null")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "country")
    @NotNull(message = "The country cannot be null")
    private Country country;
    
    @ManyToMany(mappedBy = "market")
    @NotNull(message = "The market cannot be null")
    private List<Principal> principal;
    
    public Market() {
    }
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Principal> getPrincipal() {
		return principal;
	}

	public void setPrincipal(List<Principal> principal) {
		this.principal = principal;
	}

    @Override
    public String toString() {
        return "Market{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}

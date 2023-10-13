package com.apidecrypto.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Maximiliano Almiron
 *
 */
@EqualsAndHashCode
@Entity
public class Principal {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @Column(name = "description")
    @NotNull(message = "The description cannot be null")
    private String description;
    
    @ManyToMany()
    @JoinTable(
        name = "principal_market",
        joinColumns = @JoinColumn(name = "principal_id"),
        inverseJoinColumns = @JoinColumn(name = "market_id")
    )
    @NotNull(message = "The markets cannot be null")
    private List<Market> market;
    
    public Principal() {
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonBackReference
	public List<Market> getMarket() {
		return market;
	}

	public void setMarket(List<Market> market) {
		this.market = market;
	}
	
    @Override
    public String toString() {
        return "Principal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", market='" + market + '\'' +
                '}';
    }

}

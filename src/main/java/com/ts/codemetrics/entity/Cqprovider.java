package com.ts.codemetrics.entity;
// Generated 3 Jan, 2020 1:55:17 PM by Hibernate Tools 5.1.10.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cqprovider generated by hbm2java
 */
@Entity
@Table(name = "CQProvider")
public class Cqprovider implements java.io.Serializable {

	private Integer id;
	private String name;
	private String description;
	private String url;
	private Set<Cqconfig> cqconfigs = new HashSet<Cqconfig>(0);

	public Cqprovider() {
	}

	public Cqprovider(String name, String description, String url, Set<Cqconfig> cqconfigs) {
		this.name = name;
		this.description = description;
		this.url = url;
		this.cqconfigs = cqconfigs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "URL", length = 1000)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cqprovider")
	public Set<Cqconfig> getCqconfigs() {
		return this.cqconfigs;
	}

	public void setCqconfigs(Set<Cqconfig> cqconfigs) {
		this.cqconfigs = cqconfigs;
	}

}

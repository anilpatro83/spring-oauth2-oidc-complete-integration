package com.spring.oauth2.oidc.dto;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_authorities")
public class UserAuthority implements Serializable {

	private static final long serialVersionUID = -7827484485958706617L;

	@Id
	private Long id;
	@Column(name = "user_id")
	private Long userId;
	@Column(name = "authority_id")
	private Long authorityId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}
	
}

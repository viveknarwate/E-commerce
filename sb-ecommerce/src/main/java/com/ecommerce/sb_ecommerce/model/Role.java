package com.ecommerce.sb_ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(length=20,name="role_name")
	private AppRole roleName;
	
	public Role(AppRole roleName) {
		this.roleName=roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public AppRole getRoleName() {
		return roleName;
	}

	public void setRoleName(AppRole roleName) {
		this.roleName = roleName;
	}

	public Role(Integer roleId, AppRole roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

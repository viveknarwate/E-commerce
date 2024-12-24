package com.ecommerce.sb_ecommerce.model;

import java.util.HashSet;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users",
       uniqueConstraints= {
    		   @UniqueConstraint(columnNames = "userName"),
    		   @UniqueConstraint(columnNames = "email")
                })
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
	private Long userId;
    
    @NotBlank
    @Size(max=20)
    @Column(name="userName")
    private String userName;
    
    @NotBlank
    @Size(max=50)
    @Email
    @Column(name="email")
    private String email;
    
    @NotBlank
    @Size(max=120)
    private String password;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(@NotBlank @Size(max = 20) String userName, @NotBlank @Size(max = 50) @Email String email,
			@NotBlank @Size(max = 120) String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
    
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},
			               fetch=FetchType.EAGER)
	@JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id"),
	            inverseJoinColumns  = @JoinColumn(name="role_id"))
    private Set<Role>roles=new HashSet<>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
	@OneToMany(mappedBy="user",
			  cascade = {CascadeType.PERSIST,CascadeType.MERGE},
			  orphanRemoval =true)
	private Set<Product>products;

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="user_address",
	          joinColumns = @JoinColumn(name="user_id"),
	          inverseJoinColumns=@JoinColumn(name="address_id"))
	private List<Address>addresses=new ArrayList<>();
	
	
}

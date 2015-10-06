package org.internetbank.springdao.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@NamedQueries({
@NamedQuery(name = "UserRole.getAll", query = "SELECT e from UserRolePojo e")})
public class UserRolePojo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<UserPojo> users = new HashSet<UserPojo>();
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "roleUser")
	private Set<OperationPojo> operation ;

	public UserRolePojo() {
		super();

	}

	public UserRolePojo(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserPojo> getUsers() {
		return users;
	}

	public void setUsers(Set<UserPojo> users) {
		this.users = users;
	}
	
	

	public Set<OperationPojo> getOperation() {
		return operation;
	}

	public void setOperation(Set<OperationPojo> operation) {
		this.operation = operation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRolePojo other = (UserRolePojo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRolePojo [id=" + id + ", name=" + name + "]";
	}

}

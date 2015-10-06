package org.internetbank.springdao.pojo;

import java.io.Serializable;
import java.util.Set;






import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "operations")
@NamedQueries({
@NamedQuery(name = "Operation.getAll", query = "SELECT e from OperationPojo e"),
@NamedQuery(name = "GetOperationByRole", query = "SELECT e FROM OperationPojo e WHERE e.role =:role")})
public class OperationPojo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name", length = 32)
	private String name;
	
	@Column(name = "account", length = 32)
	private long account;
	
	@Column(name = "role", length = 32)
	private long role;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="role", insertable=false, updatable=false, 
    nullable=false )
	private UserRolePojo roleUser;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "operation")
	private Set<ArchivePojo> records ;
	
	public OperationPojo() {
		super();
	}
	
	
	public OperationPojo(String name, long account, long role) {
		super();
		this.name = name;
		this.account = account;
		this.role = role;
	}
	

	public OperationPojo(Integer id, String name, long account, long role) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.role = role;
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


	public long getAccount() {
		return account;
	}


	public void setAccount(long account) {
		this.account = account;
	}


	public long getRole() {
		return role;
	}


	public void setRole(long role) {
		this.role = role;
	}
	


	public Set<ArchivePojo> getRecords() {
		return records;
	}


	public void setRecords(Set<ArchivePojo> records) {
		this.records = records;
	}
	
	


	public UserRolePojo getRoleUser() {
		return roleUser;
	}


	public void setRoleUser(UserRolePojo roleUser) {
		this.roleUser = roleUser;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (account ^ (account >>> 32));
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
		OperationPojo other = (OperationPojo) obj;
		if (account != other.account)
			return false;
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
		if (role != other.role)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "OperationPojo [id=" + id + ", name=" + name + ", account="
				+ account + ", role=" + role + "]";
	}

}

package org.internetbank.springdao.pojo;

import java.io.Serializable;

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
import javax.persistence.Table;

@Entity
@Table(name = "archive")
@NamedQueries({
@NamedQuery(name = "Archive.getAll", query = "SELECT e from ArchivePojo e"),
@NamedQuery(name = "GetArchiveByUserID", query = "SELECT e FROM ArchivePojo e WHERE e.userId =:userId")})
public class ArchivePojo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "operation_id")
	private Integer operationId;
	
	@Column(name = "sum")
	private Long sum;
	
	@Column(name = "date")
	private String date;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id", insertable=false, updatable=false, 
            nullable=false )
	private UserPojo user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="operation_id", insertable=false, updatable=false, 
            nullable=false )
	private OperationPojo operation;

	public ArchivePojo() {
		super();
	}

	public ArchivePojo(Integer id, Integer userId, Integer operationId,
			Long sum, String date) {
		super();
		this.id = id;
		this.userId = userId;
		this.operationId = operationId;
		this.sum = sum;
		this.date = date;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getOperationId() {
		return operationId;
	}


	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}


	public Long getSum() {
		return sum;
	}


	public void setSum(Long sum) {
		this.sum = sum;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	
	
	public UserPojo getUser() {
		return user;
	}


	public void setUser(UserPojo user) {
		this.user = user;
	}
	

	public OperationPojo getOperation() {
		return operation;
	}


	public void setOperation(OperationPojo operation) {
		this.operation = operation;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((operationId == null) ? 0 : operationId.hashCode());
		result = prime * result + ((sum == null) ? 0 : sum.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		ArchivePojo other = (ArchivePojo) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operationId == null) {
			if (other.operationId != null)
				return false;
		} else if (!operationId.equals(other.operationId))
			return false;
		if (sum == null) {
			if (other.sum != null)
				return false;
		} else if (!sum.equals(other.sum))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "ArchivePojo [id=" + id + ", userId=" + userId
				+ ", operationId=" + operationId + ", sum=" + sum + ", date="
				+ date + "\n"+"]";
	}
	
	
	
	
	

}

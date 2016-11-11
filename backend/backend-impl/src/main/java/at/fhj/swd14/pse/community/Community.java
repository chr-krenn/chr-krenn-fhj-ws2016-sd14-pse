package at.fhj.swd14.pse.community;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import at.fhj.swd14.pse.user.User;

@Entity
@Table(name = "community")
public class Community implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	public Long getId()
	{
		return this.id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	@Column
	private String name;

	public User getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

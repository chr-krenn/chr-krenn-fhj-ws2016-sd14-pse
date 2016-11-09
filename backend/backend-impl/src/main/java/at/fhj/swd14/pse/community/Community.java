package at.fhj.swd14.pse.community;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "community")
public class Community implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column
	private String name;
}

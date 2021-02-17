package eng.airlines.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import eng.airlines.model.interfaces.AirlineModelInterface;


@Table
@Entity(name = AirlinesDto.TableName)
public class AirlinesDto implements AirlineModelInterface {

	public static final String TableName = "airline_tb";

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	public AirlinesDto() {

	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}



}

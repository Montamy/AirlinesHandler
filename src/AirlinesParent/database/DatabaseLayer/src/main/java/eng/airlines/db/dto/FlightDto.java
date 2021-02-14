package eng.airlines.db.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import eng.airlines.model.interfaces.AirlineModelInterface;
import eng.airlines.model.interfaces.CityModelInterface;
import eng.airlines.model.interfaces.FlightModelInterface;


@Table
@Entity(name = FlightDto.TableName)
public class FlightDto implements FlightModelInterface {

	public static final String TableName = "flight_tb";

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "AIRLINE_ID", referencedColumnName = "ID")
	private AirlinesDto airline;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SOURCE_CITY_ID", referencedColumnName = "ID")
	private CityDto sourceCity;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DESTINATION_CITY_ID", referencedColumnName = "ID")
	private CityDto destinationCity;

	@Column(name = "DISTANCE")
	private Integer distance;

	@Column(name = "SCHEDULE_MIN")
	private Integer scheduleMin;

	public FlightDto() {

	}

	public Long getId() {
		return id;
	}

	public AirlinesDto getAirline() {
		return airline;
	}

	public CityDto getSourceCity() {
		return sourceCity;
	}

	public CityDto getDestinationCity() {
		return destinationCity;
	}

	public Integer getDistance() {
		return distance;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setAirline(AirlinesDto airline) {
		this.airline = airline;
	}

	public void setSourceCity(CityDto sourceCity) {
		this.sourceCity = sourceCity;
	}

	public void setDestinationCity(CityDto destinationCity) {
		this.destinationCity = destinationCity;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getScheduleMin() {
		return scheduleMin;
	}

	public void setScheduleMin(Integer scheduleMin) {
		this.scheduleMin = scheduleMin;
	}

	// TODO

	@Override
	public void setAirline(AirlineModelInterface airline) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSourceCity(CityModelInterface sourceCity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDestinationCity(CityModelInterface destinationCity) {
		// TODO Auto-generated method stub

	}

}

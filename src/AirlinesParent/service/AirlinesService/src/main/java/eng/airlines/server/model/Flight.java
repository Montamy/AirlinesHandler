package eng.airlines.server.model;

import eng.airlines.model.implement.BaseDbModel;
import eng.airlines.model.interfaces.AirlineModelInterface;
import eng.airlines.model.interfaces.CityModelInterface;
import eng.airlines.model.interfaces.FlightModelInterface;

public class Flight extends BaseDbModel implements FlightModelInterface {

	private AirlineModelInterface airline;
	private CityModelInterface sourceCity;
	private CityModelInterface destinationCity;
	private Integer distance;
	private Integer scheduleMin;

	public Flight() {

	}

	public Flight(Long id, AirlineModelInterface airline, CityModelInterface sourceCity, CityModelInterface destinationCity, Integer distance, Integer scheduleMin) {
		super(id);

		this.airline = airline;
		this.sourceCity = sourceCity;
		this.destinationCity = destinationCity;
		this.distance = distance;
		this.scheduleMin = scheduleMin;
	}

	public AirlineModelInterface getAirline() {
		return airline;
	}

	public CityModelInterface getSourceCity() {
		return sourceCity;
	}

	public CityModelInterface getDestinationCity() {
		return destinationCity;
	}

	public Integer getDistance() {
		return distance;
	}

	public Integer getScheduleMin() {
		return scheduleMin;
	}

	public void setAirline(AirlineModelInterface airline) {
		this.airline = airline;
	}

	public void setSourceCity(CityModelInterface sourceCity) {
		this.sourceCity = sourceCity;
	}

	public void setDestinationCity(CityModelInterface destinationCity) {
		this.destinationCity = destinationCity;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public void setScheduleMin(Integer scheduleMin) {
		this.scheduleMin = scheduleMin;
	}

	@Override
	public String toString() {
		return "[ id: " + id + " ,airline: " + airline + " ,sourceCity: " + sourceCity + " ,destinationCity: " + destinationCity + " ,distance: " + distance + " ,scheduleMin: "
				+ scheduleMin + " ]";
	}

}

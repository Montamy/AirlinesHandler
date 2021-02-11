package eng.airlines.server.model;

import eng.airlines.interfaces.AirlineModelInterface;
import eng.airlines.interfaces.CityModelInterface;
import eng.airlines.interfaces.FlightModelInterface;

public class Flight implements FlightModelInterface {

	private AirlineModelInterface airline;
	private CityModelInterface sourceCity;
	private CityModelInterface destinationCity;
	private Integer distance;
	private Integer scheduleMin;

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


}

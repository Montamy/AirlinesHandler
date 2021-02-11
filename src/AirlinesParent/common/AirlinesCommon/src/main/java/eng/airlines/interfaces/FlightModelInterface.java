package eng.airlines.interfaces;

public interface FlightModelInterface {

	public AirlineModelInterface getAirline();

	public void setAirline(AirlineModelInterface airline);

	public CityModelInterface getSourceCity();

	public void setSourceCity(CityModelInterface sourceCity);

	public CityModelInterface getDestinationCity();

	public void setDestinationCity(CityModelInterface destinationCity);

	public Integer getDistance();

	public void setDistance(Integer distance);

	public Integer getScheduleMin();

	public void setScheduleMin(Integer scheduleMin);

}

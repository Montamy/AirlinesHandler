package eng.airlines.dijsktra.calculator;

import java.util.List;

import eng.airlines.model.interfaces.FlightModelInterface;
import eng.airlines.model.type.PathEdgeWeightType;

public interface PathCalculatorInterface {

	public List<FlightModelInterface> calculatePath(PathEdgeWeightType type, Long cityId1, Long cityId2, Long idAirline);
}

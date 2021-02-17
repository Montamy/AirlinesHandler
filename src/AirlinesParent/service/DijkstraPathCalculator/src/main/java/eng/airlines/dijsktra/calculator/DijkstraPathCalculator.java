package eng.airlines.dijsktra.calculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import eng.airlines.db.interfaces.AirlinesDbInterface;
import eng.airlines.db.interfaces.CityDbInterface;
import eng.airlines.db.interfaces.FlightDbInterface;
import eng.airlines.dijkstra.model.Node;
import eng.airlines.model.interfaces.FlightModelInterface;
import eng.airlines.model.type.PathEdgeWeightType;

@Service
public class DijkstraPathCalculator implements PathCalculatorInterface {

	private static Logger logger = LogManager.getLogger(DijkstraPathCalculator.class);

	@Autowired
	private FlightDbInterface flightsDbInterface;

	@Autowired
	private CityDbInterface citysDbInterface;

	@Autowired
	private AirlinesDbInterface airlinesDbInterface;

	@Override
	public List<FlightModelInterface> calculatePath(PathEdgeWeightType type, Long cityId1, Long cityId2, Long idAirline) {

		// TODO validate is cities and airlines ar available

		// create all nodes (list i guess)
		List<Node> nodes = createNodes(type, cityId1, cityId2, idAirline);

		// city1 add to unsettled nodes
		List<Node> unsettledNodes = initUnsettledNodes(nodes, cityId1);



		// while unsetted nodes not empty
		while (!unsettledNodes.isEmpty()) {

			// choose a node from unsetted nodes (and cut it out to)
			// this legyen a leg kisebb distancejű
			Node choosedNode = unsettledNodes
					.stream()
					.min(Comparator.comparing(Node::getMinDistance))
					.get();

			// calculate new by keeping ha lowest
			List<Node> reachedUnsettedNodes = choosedNode.reCalcNeighborsDistance();

			// add neighbors not settled yet to unsettled nodes
			unsettledNodes.addAll(reachedUnsettedNodes);

			unsettledNodes.remove(choosedNode);

		}



		logger.info("Lehet megvan, debug point");

		return null;
	}

	private List<Node> initUnsettledNodes(List<Node> allnodes, Long startCity) {
		List<Node> unsettledNodes = new ArrayList<Node>();

		Node firstNode = allnodes.stream().filter(x -> x.getCity().getId() == startCity).findFirst().get();
		firstNode.setIsSettled(true);

		unsettledNodes.add(firstNode);

		return unsettledNodes;

	}

	private List<Node> createNodes(PathEdgeWeightType type, Long cityId1, Long cityId2, Long idAirline) {

		List<FlightModelInterface> availableFlights = getAvailableFlights(idAirline);
		List<Node> availableNodes = citysDbInterface.findAll()
				.stream()
				.map(x-> new Node(null,Long.MAX_VALUE,x))
				.collect(Collectors.toList());
		
		for (Node node : availableNodes) {
			
			if (node.getCity().getId() == cityId1) {
				node.setMinDistance(0L);
			}

			MultiValueMap<Node, FlightModelInterface> predecessor = new LinkedMultiValueMap<Node, FlightModelInterface>();

			List<FlightModelInterface> flightsFromCity = availableFlights
					.stream()
					.filter(flight -> flight.getSourceCity().getId() == node.getCity().getId())
					.collect(Collectors.toList());
			
			for (FlightModelInterface flight : flightsFromCity) {
				Node destinationNode = availableNodes.stream()
					.filter(y-> y.getCity().getId() == flight.getDestinationCity().getId())
					.findFirst()
					.get();

				predecessor.add(destinationNode, flight);
			}

			node.setPredecessor(predecessor);
		}

		return availableNodes;
	}

	private List<FlightModelInterface> getAvailableFlights(Long idAirline) {
		if (idAirline != null) {
			return flightsDbInterface.findAirlinesFlightsByAirlineId(idAirline);
		} else {
			return  flightsDbInterface.findAll()
					.stream()
					// TODO working map .map(x -> mapper.map(x, Flight.class))
					.collect(Collectors.toList());
		}
	}



}

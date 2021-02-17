package eng.airlines.dijkstra.model;



import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import eng.airlines.model.interfaces.CityModelInterface;
import eng.airlines.model.interfaces.FlightModelInterface;

public class Node {
	
	private Long minDistance;
	private CityModelInterface city;
	private MultiValueMap<Node, FlightModelInterface> predecessor; // Node,Long(distance)
	private Boolean isSettled;

	public Node(MultiValueMap<Node, FlightModelInterface> predecessor, Long minDistance, CityModelInterface city) {
		this.minDistance = minDistance;
		this.isSettled = false;
		this.city = city;
		
		if (predecessor != null) {
			this.predecessor = predecessor;
		} else {
			this.predecessor = new LinkedMultiValueMap<Node, FlightModelInterface>();
		}
	}

	public List<Node> reCalcNeighborsDistance() {
		List<Node> unSettledNodes = new ArrayList<Node>();

		predecessor.forEach((key, valueList) -> {
			key.setMinDistanceIfLower(this.minDistance, valueList.stream().mapToLong(x -> x.getDistance()).min().orElseThrow());
			if (!key.isSettled) {
				key.setIsSettled(true);
				unSettledNodes.add(key);
			}
		});

		return unSettledNodes;
	}

	public void setMinDistanceIfLower(Long base, Long weightDistance) {
		if (this.minDistance > (base + weightDistance))
			this.minDistance = (base + weightDistance);
	}


	public Long getMinDistance() {
		return minDistance;
	}

	public CityModelInterface getCity() {
		return city;
	}

	public MultiValueMap<Node, FlightModelInterface> getPredecessor() {
		return predecessor;
	}

	public void setCity(CityModelInterface city) {
		this.city = city;
	}

	public void setPredecessor(MultiValueMap<Node, FlightModelInterface> predecessor) {
		this.predecessor = predecessor;
	}


	public Boolean getIsSettled() {
		return isSettled;
	}

	public void setMinDistance(Long minDistance) {
		this.minDistance = minDistance;
	}

	public void setIsSettled(Boolean isSettled) {
		this.isSettled = isSettled;
	}

}

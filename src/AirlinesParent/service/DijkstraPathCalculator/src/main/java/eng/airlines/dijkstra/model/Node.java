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
	private MultiValueMap<Node, FlightModelInterface> neighbors; // Node,Long(distance)
	private Boolean isSettled;
	private Node predecessor;

	public Node(MultiValueMap<Node, FlightModelInterface> neighbors, Long minDistance, CityModelInterface city) {
		this.minDistance = minDistance;
		this.isSettled = false;
		this.city = city;
		this.predecessor = null;
		
		if (neighbors != null) {
			this.neighbors = neighbors;
		} else {
			this.neighbors = new LinkedMultiValueMap<Node, FlightModelInterface>();
		}
	}

	public List<Node> reCalcNeighborsDistance() {
		List<Node> unSettledNodes = new ArrayList<Node>();

		neighbors.forEach((key, valueList) -> {
			key.setMinDistanceIfLower(this, this.minDistance, valueList.stream().mapToLong(x -> x.getDistance()).min().orElseThrow());
			if (!key.isSettled) {
				key.setIsSettled(true);
				unSettledNodes.add(key);
			}
		});

		return unSettledNodes;
	}

	public void setMinDistanceIfLower(Node sender, Long base, Long weightDistance) {
		if (this.minDistance > (base + weightDistance))
		{
			this.predecessor = sender;
			this.minDistance = (base + weightDistance);
		}
	}


	public Long getMinDistance() {
		return minDistance;
	}

	public CityModelInterface getCity() {
		return city;
	}

	public MultiValueMap<Node, FlightModelInterface> getNeighbors() {
		return neighbors;
	}

	public void setCity(CityModelInterface city) {
		this.city = city;
	}

	public void setNeighbors(MultiValueMap<Node, FlightModelInterface> neighbors) {
		this.neighbors = neighbors;
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

	public Node getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Node predecessor) {
		this.predecessor = predecessor;
	}

}

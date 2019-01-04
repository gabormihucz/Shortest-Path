import java.util.Stack;

public class Path {

	
	int distance; /** current distance from source */
	private static Stack<Vertex> path; /** stack that contains the vertices on the path */

	
	public Path(Vertex j) {
		distance = 0;
		path = new Stack<Vertex>();
		path.push(j);

}

	/** getters and setters */
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance=distance;
	}
	
	public void addToDistance(int distance) {
		this.distance+=distance;
	}
	
	public void subtractFromDistance(int distance) {
		this.distance-=distance;
	}

	public Stack<Vertex> getPath() {
		return path;
	}

	public void setPath(Stack<Vertex> path) {
		this.path = path;
	}

	
	
}
import java.util.LinkedList;


/**
 * class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices = 0; // number of vertices
	private LinkedList<Integer> shortestPath;  // linked list that will store the shortest distance and its path
	private Vertex source;
	private Vertex destination;
	private Path currentPath;
	private int distanceOfBestPath;
	
	// possibly other fields representing properties of the graph

	/**
	 * creates a new instance of Graph with n vertices
	 */
	public Graph(int n, int j, int k) {
		shortestPath = new LinkedList<Integer>();
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++)
			vertices[i] = new Vertex(i);

		/** set up the source and destination vertices 
		 * and the current path
		 * */
		
		source = vertices[j];
		destination = vertices[k];
		currentPath = new Path(source);

		
		distanceOfBestPath = Integer.MAX_VALUE;
		shortestPath = new LinkedList<Integer>();

	}
		
	public int size() {
		return numVertices;
	}

	public Vertex getVertex(int i) {
		return vertices[i];
	}

	public void setVertex(int i) {
		vertices[i] = new Vertex(i);
	}


	public LinkedList<Integer> backtrack() {

		/** the current vertex to examine is on the top of the stack */
		Vertex currentVertex = currentPath.getPath().peek();
		for (AdjListNode adjacentNode : currentVertex.getAdjList()) {

			Vertex adjacentVertex = vertices[adjacentNode.getVertexIndex()];

			/** for each adjacent vertex that has not been visited
			 * we push it on the stack, mark it visited and add its distance to the path's distance so far
			 */
			if (!adjacentVertex.visited) {

				currentPath.getPath().push(adjacentVertex);
				currentPath.addToDistance(adjacentNode.getWeight());
				adjacentVertex.setVisited(true);

				/** if the current path's distance is shorter than the shortest distance so far */
				
				if (currentPath.getDistance() < distanceOfBestPath) {
					
					/** if the currently evaluated adjacent vertex is our destination
					 * update the best paths's distance and list of vertices
					 * and update the linked list containing the results
					 * otherwise recursively call the function
					 * */
					if (adjacentVertex.equals(destination)) {
						
						distanceOfBestPath = currentPath.getDistance();
						shortestPath.clear();
						shortestPath.add(currentPath.getDistance());

						for (Vertex vertexOnCurrentPath : currentPath.getPath()) {
							shortestPath.add(vertexOnCurrentPath.getIndex());
						}
					} else {
						backtrack();
					}
				}
				/** if the path is longer than the shortest distance so far
				 * remove the added distance, remove the added vertex from the path, and unmark the vertex
				 */
				currentPath.subtractFromDistance(adjacentNode.getWeight());
				currentPath.getPath().pop();
				adjacentVertex.setVisited(false);
				
			}
		}
		return shortestPath;
	}

	
	/**
	 * getters and setters for the Source and Destination vertices
	 * due to setting them in main after creating the Graph in main with default 0
	 *  */
	
	public Vertex getSource() {
		return source;
	}

	public void setSource(int j) {
		this.source = vertices[j];
		currentPath = new Path(this.source);
	}

	public Vertex getDestination() {
		return destination;
	}

	public void setDestination(int k) {
		this.destination = vertices[k];

	}

	
}

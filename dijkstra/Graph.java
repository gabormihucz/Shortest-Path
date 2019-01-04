import java.util.Stack;

/**
 * class to represent an undirected graph using adjacency lists
 */
public class Graph {

	private Vertex[] vertices; // the (array of) vertices
	private int numVertices; // number of vertices


	// possibly other fields representing properties of the graph

	/**
	 * creates a new instance of Graph with n vertices
	 */
	public Graph(int n) {
		numVertices = n;
		vertices = new Vertex[n];
		for (int i = 0; i < n; i++) {
			vertices[i] = new Vertex(i);
		}

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

	public Stack<Integer> dijkstra(int startIndex, int endIndex) {

		/**
		 * Set distance of source from itself 0 
		 */
		Vertex source = vertices[startIndex];
		source.setDistanceFromSource(0);

		/** while not all of the vertices have been visited */
		
		while (!allVisited(vertices)) {
			
			Vertex currentVertex = closestToSource(vertices); /** find the vertex closest to the source*/
			
			/** if no vertex was found, break out of the loop, no path between start and end */
			
			if (currentVertex == null) break;

			/** mark the vertex as visited */
			
			vertices[currentVertex.getIndex()].setVisited(true);

			/** check each adjacent vertex, and if
			 *  its distance from the source is greater than the distance of the current vertex + the distance between the adjacent vertex and the current vertex
			 *  update the distance of the adjacent vertex
			 *  and set the predecessor of the adjacent vertex to the current vertex
			 * */
			
			for (AdjListNode adjacentVertex : currentVertex.getAdjList()) {
				Vertex attemptedVertex = vertices[adjacentVertex.getVertexIndex()];

				if ((attemptedVertex.getDistanceFromSource() > currentVertex.getDistanceFromSource() + adjacentVertex.getWeight())) {
					attemptedVertex.setDistanceFromSource(currentVertex.getDistanceFromSource() + adjacentVertex.getWeight());
					attemptedVertex.setPredecessor(currentVertex.getIndex());
				}
			}
		}

		/**
		 * populate stack for the output
		 * push the predecessors starting from the end vertex
		 * and the last element will be the distance
		 * if we keep popping the stack, we will get the correct order of output
		 */

		Stack<Integer> results = new Stack<Integer>();

		int predecessor = endIndex;
		results.push(predecessor);
		while (predecessor != -1) {
			predecessor = vertices[predecessor].getPredecessor();
			results.push(predecessor);
			if (predecessor == startIndex)
				predecessor = -1;
		}

		int distance = vertices[endIndex].getDistanceFromSource();
		results.push(distance);
		
		return results;
	}

	/** 
	 * this method takes in the vertices array
	 * returns false if any of the vertices have not been visited yet
	 * true otherwise
	 */
	
	private static boolean allVisited(Vertex[] vertexArray) {
		for (Vertex currentVertex : vertexArray) {
			if (!currentVertex.getVisited())
				return false;
		}
		return true;
	}

	/** takes in the vertices array and returns the vertex closest to the source
	 * from the vertices that have not been visited yet 
	 * */
	
	private static Vertex closestToSource(Vertex[] vertexArray) {
		Vertex closestVertex = null;
		int minDistance = Integer.MAX_VALUE;
		for (Vertex currentVertex : vertexArray) {
			if (!vertexArray[currentVertex.getIndex()].getVisited()) {
				if (currentVertex.getDistanceFromSource() < minDistance) {
					minDistance = currentVertex.getDistanceFromSource();
					closestVertex = currentVertex;
				}
			}
		}
		return closestVertex;
	}
}

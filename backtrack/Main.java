import java.io.*;
import java.util.*;

/**
 program to find shortest path using the backtrack search algorithm
 */
public class Main {

	public static void main(String[] args) throws IOException {

		long start = System.currentTimeMillis();

		String inputFileName = args[0]; // input file name
		String outputFileName = args[1]; // output file name
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		
		String line = in.nextLine();
		Scanner lineScanner = new Scanner(line);
		int numVertices = lineScanner.nextInt();

		/** Create Graph based on input file */
		
		Graph G = new Graph(numVertices,0,0);
		for (int i = 0; i < numVertices; i++) {
			// update information for vertex with index i
			line = in.nextLine(); // read information
			lineScanner = new Scanner(line);
			for (int j = 0; j < numVertices; j++) {
				int weight = lineScanner.nextInt();
				if (weight != 0)
					G.getVertex(i).addToAdjList(j, weight);
			}
		}
		line = in.nextLine();
		String[] spl = line.split(" ");
		int j = Integer.parseInt(spl[0]);
		int k = Integer.parseInt(spl[1]);
		reader.close();

		/** Adjust start and end vertices based on the last line of the input file */
		
		G.setSource(j);
		G.setDestination(k);
		
		/** Get the shortest distance and the list of vertices on that path using backtrack */
		
		LinkedList<Integer> shortest = G.backtrack();
		
		
		/** write the result into the the specified output file */
		
		FileWriter writer = new FileWriter(outputFileName, true);
		
		writer.write("==="+args[0]+"===");
		/** if the returned list is empty, that means there is no path at all */
		if (shortest.isEmpty()) {
			writer.write("\n\nThere is no path between vertices " + j + " and " + k);
		} else {
			/** first element in the list is the distance, then the vertices */
			writer.write("\n\nThe shortest distance from vertex " + j + " and " + k + " is: " + shortest.get(0));
			writer.write("\nThe shortest path is:");
			for (int i = 1; i < shortest.size(); i++) {
				writer.write(" " + shortest.get(i));
			}
		}

		/** end time and print the elapsed time */
		long end = System.currentTimeMillis();
		writer.write("\nElapsed time: " + (end - start) + " milliseconds\n\n");
		
		
		writer.close();
		
	}

}

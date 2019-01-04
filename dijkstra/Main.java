import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		long start = System.currentTimeMillis();
		String inputFileName = args[0];
		String outputFileName = args[1];
		FileReader reader = new FileReader(inputFileName);
		Scanner in = new Scanner(reader);
		String line = in.nextLine();
		Scanner lineScanner = new Scanner(line);
		int numVertices = lineScanner.nextInt();

		// insert code here to build the graph from the input file
		Graph G = new Graph(numVertices);
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
		/** get start and end indices from the last line */
		line = in.nextLine();
		String[] splitLine = line.split(" ");
		int startIndex = Integer.parseInt(splitLine[0]);
		int endIndex = Integer.parseInt(splitLine[1]);
		reader.close();

		Stack<Integer> results = G.dijkstra(startIndex, endIndex);
		
		
		FileWriter writer = new FileWriter(outputFileName, true);
		
		/** pop results to get thre required output */		
		
		writer.write("==="+args[0]+"===");
		int distance = results.pop();
		if ( distance == Integer.MAX_VALUE) {
			writer.write((("\n\nThere is no path between vertices " + startIndex + " and " + endIndex)));
		} else {
			writer.write("\n\nThe shortest distance from vertex " + startIndex + " and " + endIndex + " is: " + distance);
			writer.write("\nThe shortest path is:");
			while (!results.isEmpty()) {
				writer.write(" " + results.pop());
			}
		}
		
		long end = System.currentTimeMillis();
		writer.write("\nElapsed time: " + (end - start) + " milliseconds\n\n");
		
		writer.close();
		

		
	}

}
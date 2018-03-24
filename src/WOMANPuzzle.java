import java.util.ArrayList;
import java.util.Scanner;

public class WOMANPuzzle {
    public static Graph graph;
    private static String source = "Washington";
    private static String sink = "District of Columbia";


    public static void main(String args[]) {
        graph = new Graph("/Users/Sarah/Desktop/USStates.txt");
        graph.readGraph();

        printPath(graph.Search(graph, source, sink), source, sink);
    }

    private static void printPath(ArrayList<String> path, String source, String sink) {
        if (path == null) {
            System.out.println("No. There is no way to get from " + source + " to " + sink + ".");
        } else {
            System.out.println("Yes. To get from " + source + " to " + sink + ", march as follows:");
            for (int i = path.size() - 1; i > 0; i--) {
                System.out.print(path.get(i) + ", ");
            }
            System.out.println(path.get(0));
        }
    }
}

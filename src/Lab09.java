import java.util.ArrayList;
import java.util.Scanner;

public class Lab09 {
    public static Graph graph;
    public static boolean EXIT = false;
    private static String state = new String();


    public static void main(String args[]) {
        graph = new Graph("/Users/Sarah/Desktop/USStates.txt");
        graph.readGraph();

        //allow the user to query the newly read in information
        while (!EXIT) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("If you would like to stop please enter 'exit'. Otherwise, please input a state name to get the adjacent states: ");
            state = scanner.nextLine().trim();

            if (state.toUpperCase().equals("EXIT")) {
                System.out.println("Thank you! Have a good day.");
                EXIT = true;
            } else {
                ArrayList<String> searchResult = graph.getAdjacency(state.trim().toUpperCase());
                if (searchResult.size() > 0) {
                    System.out.print(state + " has the following neighbors: ");
                    for (int i = 0; i < searchResult.size(); i++) {
                        System.out.print(searchResult.get(i));
                    }
                    System.out.println();
                } else {
                    System.out.println(state + " has no neighbors.");
                }
            }
        }

        printPath(graph.Search(graph, "Washington", "District of Columbia"));
//        System.out.println(graph.Search(graph, "Washington", "Maryland"));
    }

    private static void printPath(ArrayList<String> path) {
        for (int i = path.size() - 1; i > 0; i--) {
            System.out.print(path.get(i) + ", ");
        }
        System.out.println(path.get(0));
    }
}

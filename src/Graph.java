import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    String sourceFile;
    ArrayList<String> states;
    HashMap<String, ArrayList<String>> graph;

    public Graph(String sourceFile) {
        this.sourceFile = sourceFile;
        this.states = new ArrayList<>();
        this.graph = new HashMap<>();
    }

    public void readGraph() {
        //read contents of file into the graph's hash map
        try {
            File file = new File(this.sourceFile);
            BufferedReader in = new BufferedReader(new FileReader(file));

            in.readLine(); //get rid of the header line
            String inputLine;
            String[] tokens;

            while ((inputLine = in.readLine()) != null) {
                tokens = inputLine.split(",");
                states.add(tokens[0].trim()); //all the 50 states
                ArrayList<String> adjacentStates = new ArrayList<>();
                makeList(tokens, adjacentStates);
                this.graph.put(tokens[0].trim().toUpperCase(), adjacentStates);
            }

            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    } //readGraph

    private void makeList(String[] tokens, ArrayList<String> adjacentStates) {
        for (int i = 1; i < tokens.length - 1; i++) {
            adjacentStates.add(tokens[i].trim() + " ");
        }
        if (tokens.length > 1) {
            adjacentStates.add(tokens[tokens.length - 1].trim());
        }
    } //makeList

    public ArrayList<String> getAdjacency(String state) {
        return this.graph.get(state.toUpperCase());
    }

    public ArrayList<String> Search(Graph G, String start, String end) {
        String[] pred = new String[this.states.size()];
        Boolean[] visited = new Boolean[this.states.size()];
        Stack<String> Q = new Stack<>();

        for (int i = 0; i < this.states.size(); i++) {
            pred[i] = null;
            visited[i] = false;
        }

        Q.push(start);

        while (!Q.empty()) {
            String u = Q.pop();
            if (u.equals(end)) {
                return buildPath(pred, start, end);
            } else {
                visited[this.states.indexOf(u)] = true;

                for (String adjacent : G.getAdjacency(u)) {
                    if (startsWithWOMAN(adjacent.trim(), end.trim()) && !visited[this.states.indexOf(adjacent.trim())] && !Q.contains(adjacent.trim())) {
                        pred[this.states.indexOf(adjacent.trim())] = u;
                        Q.push(adjacent.trim());
                    }
                }
            }
        }

        return null;
    }

    private boolean startsWithWOMAN(String s, String end) {
        return (s.charAt(0) == 'W' || s.charAt(0) == 'O' || s.charAt(0) == 'M' || s.charAt(0) == 'A' || s.charAt(0) == 'N' || s.equals(end));
    }

    private ArrayList<String> buildPath(String[] pred, String start, String end) {
        ArrayList<String> returnValue = new ArrayList<>();

        String before = end;
        while(!before.equals(start)) {
            returnValue.add(before);
            before = pred[states.indexOf(before)];
        }

        returnValue.add(start);
        return returnValue;
    }

} //Graph

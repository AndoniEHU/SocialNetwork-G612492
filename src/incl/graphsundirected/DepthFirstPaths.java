package incl.graphsundirected;


import incl.SedgewickCommon.Stack;

/*************************************************************************
 *  Compilation:  javac DepthFirstPaths.java
 *  Execution:    java DepthFirstPaths G s
 *  Dependencies: Graph.java Stack.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41undirected/tinyCG.txt
 *
 *  Run depth first search on an undirected graph.
 *  Runs in O(E + V) time.
 *
 *  %  java Graph tinyCG.txt
 *  6 8
 *  0: 2 1 5 
 *  1: 0 2 
 *  2: 0 1 3 4 
 *  3: 5 4 2 
 *  4: 3 2 
 *  5: 3 0 
 *
 *  % java DepthFirstPaths tinyCG.txt 0
 *  0 to 0:  0
 *  0 to 1:  0-2-1
 *  0 to 2:  0-2
 *  0 to 3:  0-2-3
 *  0 to 4:  0-2-3-4
 *  0 to 5:  0-2-3-5
 *
 *************************************************************************/

public class DepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private final int s;         // source vertex

    // Default constructor: Used to compute DFS to all nodes
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // Alternative constructor: Used to find the alternative path to node t
    public DepthFirstPaths(Graph G, int s, int t) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        altDFS(G, s, t);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    // alternate depth first search from v: find the SECOND path when looking for node t
    private void altDFS(Graph G, int v, int t) {
        marked[v] = true;
        boolean tFound = false;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                // We discard the first path we find
                if (w==t && !tFound) {
                    tFound = true;
                }
                else if (w==t && tFound) { // When we find a second path, we return it
                    edgeTo[w] = t;
                    marked[w]=true;
                    return;
                }
                else {
                    edgeTo[w] = v;
                    dfs(G, w);
                    marked[w] = false; // We mark this edge as unvisited so it can be used in another path
                }
            }
        }
    }


    // is there a path between s and v?
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // return a path between s to v; null if no such path
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

}

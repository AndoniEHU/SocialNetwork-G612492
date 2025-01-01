package incl.graphsundirected;

import classes.Person;
import classes.Relationship;
import incl.DoubleOrderedList;
import incl.OrderedList;


import java.util.Iterator;

/*************************************************************************
 *  Compilation:  javac SymbolGraph.java
 *  Execution:    java SymbolGraph filename.txt delimiter
 *  Dependencies: ST.java Graph.java In.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41undirected/routes.txt
 *                http://algs4.cs.princeton.edu/41undirected/movies.txt
 *  
 *  %  java SymbolGraph routes.txt " "
 *  JFK
 *     MCO
 *     ATL
 *     ORD
 *  LAX
 *     PHX
 *     LAS
 *
 *  % java SymbolGraph movies.txt "/"
 *  Tin Men (1987)
 *     Hershey, Barbara
 *     Geppi, Cindy
 *     Jones, Kathy (II)
 *     Herr, Marcia
 *     ...
 *     Blumenfeld, Alan
 *     DeBoy, David
 *  Bacon, Kevin
 *     Woodsman, The (2004)
 *     Wild Things (1998)
 *     Where the Truth Lies (2005)
 *     Tremors (1990)
 *     ...
 *     Apollo 13 (1995)
 *     Animal House (1978)
 *
 *************************************************************************/

public class SymbolGraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Graph G;


    public SymbolGraph(DoubleOrderedList<Person> list) {
        st = new ST<String, Integer>();
        Iterator<Person> it = list.iterator();
        while (it.hasNext()) {
            Person p = it.next();
            if(!st.contains(p.getId())) {
                st.put(p.getId(), st.size());
            }
        }

        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        G = new Graph(st.size());
    }

    public void addEdges(OrderedList<Relationship> relations){
        for(Relationship r : relations){
            String f1 = r.getFriend1();
            String f2 = r.getFriend2();
            int v = st.get(f1);
            int w = st.get(f2);
            G.addEdge(v, w);
        }
    }
    public boolean contains(String s) {
        return st.contains(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }


}

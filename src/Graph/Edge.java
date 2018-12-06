/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

/**
 *
 * @author giova
 */
public class Edge {
    private static final int DEFAULT_WEIGHT = 1;

    Vertex origin, destination;
    int traffic;

    public Edge(Vertex origin, Vertex destination) {
        this(origin, destination, DEFAULT_WEIGHT);
    }

    public Edge(Vertex origin, Vertex destination, int traffic) {
        super();
        this.origin = origin;
        this.destination = destination;
        this.traffic = traffic;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Edge)) return false;

        Edge _obj = (Edge) obj;
        return _obj.origin.equals(origin) && _obj.destination.equals(destination) &&
               _obj.traffic == traffic;
    }
    
    public Vertex opposite(Vertex s){
        if(s.equals(this.origin))
            return this.destination;
        else if(s.equals(this.destination))
            return this.origin;
        else{
            System.out.println("Errore in opposite");
            return null;
        }
    }
    
    @Override
    public String toString(){
        return "arco tra "+origin+" e "+destination;
    }
}

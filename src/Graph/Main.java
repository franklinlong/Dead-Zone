/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.Map;

/**
 *
 * @author giova
 */
public class Main {
    public static void main(String args[]){
        Graph g = new Graph();
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);
        Vertex v6 = new Vertex(6);
        Vertex v7 = new Vertex(7);
        Vertex v8 = new Vertex(8);
        Vertex v9 = new Vertex(9);
        Vertex v10 = new Vertex(10);
        Vertex v11 = new Vertex(11);
        Vertex v12 = new Vertex(12);
        Vertex v13 = new Vertex(13);
        
        g.insert_vertex(v1);
        g.insert_vertex(v2);
        g.insert_vertex(v3);
        g.insert_vertex(v4);
        g.insert_vertex(v5);
        g.insert_vertex(v6);
        g.insert_vertex(v7);
        g.insert_vertex(v8);
        g.insert_vertex(v9);
        g.insert_vertex(v10);
        g.insert_vertex(v11);
        g.insert_vertex(v12);  
        g.insert_vertex(v13);
        g.insert_edge(v1, v2, 10);
        g.insert_edge(v1, v4, 10);
        g.insert_edge(v2, v3, 10);
        g.insert_edge(v3, v5, 10);
        g.insert_edge(v2, v7, 10);
        g.insert_edge(v2, v9, 10);
        g.insert_edge(v9, v13, 10);
        g.insert_edge(v9, v11, 10);
        g.insert_edge(v13, v12, 10);
        g.insert_edge(v11, v10, 10);
        g.insert_edge(v10, v8, 10);
        g.insert_edge(v1, v4, 10);
        g.insert_edge(v2, v3, 10);
        g.insert_edge(v1, v11, 0);
        g.stampa();
        
        System.out.println("---------------------------------------------");
        for(Edge x: g.incident_edges(v1))
            System.out.println(x);
        
        System.out.println("---------------------------------------------");
        Map<Vertex,Edge> pMinimi = g.BFS_complete(v1);
        for(Vertex v: pMinimi.keySet()){
            System.out.println(v);
            Edge e = pMinimi.get(v);
            System.out.println(e);
        }
    }
}

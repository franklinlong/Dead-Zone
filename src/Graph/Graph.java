/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author giova
 */
public class Graph {
    private static Map<Vertex, Map<Vertex, Edge>> adjList;
    Vertex zona1;
    Vertex zona2;
    Vertex zona3;
    Vertex zona4;
    Vertex zona5;
    Vertex zona6;
    Vertex zona7;
    Vertex zona8; 
    Vertex zona9;
    Vertex zona10;
    Vertex zona11;
    Vertex zona12;
    Vertex zona13;
    Vertex zona14;
    Vertex zona15;
    Vertex zona16;
    Vertex zona17;
    Vertex zona18;
    Vertex zona19;
    Vertex zona20;
    Vertex zona21;
    Vertex zona22;
    Vertex zona23;
    Vertex zona24; 
    Vertex zona25;
    Vertex zona26;
    Vertex zona27;
    Vertex zona28;
    Vertex zona29;
    Vertex zona30;
    Vertex zona31;
    Vertex zona32;
    Vertex zona33;
    Vertex zona34;
    Vertex zona35;
    Vertex zona36;
    Vertex zona37;
    Vertex zona38;
    Vertex zona39;
    Vertex zona40; 
    Vertex zona41;
    Vertex zona42;
    Vertex zona43;
    Vertex zona44;
    Vertex zona45;
    Vertex zona46;
    Vertex zona47;
    Vertex zona48;
    Vertex zona49;
    Vertex zona50;
    Vertex zona51;
    Vertex zona52;
    Vertex zona53;
    Vertex zona54;
    Vertex zona55;
    Vertex zona56; 
    Vertex zona57;
    Vertex zona58;
    Vertex zona59;
    Vertex zona60;
    Vertex zona61;
    Vertex zona62;
    Vertex zona63;
    Vertex zona64;
    Vertex zona65;
    Vertex zona66;
    Vertex zona67;
    Vertex zona68;
    Vertex zona69;
    Vertex zona70;
    Vertex zona71;
    Vertex zona72;
    Vertex zona73;
    Vertex zona74;
    Vertex zona75;
    Vertex zona76;
    Vertex zona77;
    Vertex zona78;
    Vertex zona79;
    Vertex zona80;
    Vertex zona81;
    Vertex zona82;
    Vertex zona83;
    Vertex zona84;
    Vertex zona85;
    Vertex zona86;
    Vertex zona87;
    Vertex zona88;
    Vertex zona89;
    Vertex zona90;
    Vertex zona91;
    Vertex zona92;
    Vertex zona93;
    Vertex zona94;
    Vertex zona95;
    Vertex zona96;
    Vertex zona97;
    Vertex zona98;
    Vertex zona99;
    Vertex zona100;
    Vertex zona101;
    Vertex zona102;
    Vertex zona103;
    Vertex zona104;
    Vertex zona105;
    Vertex zona106;
    Vertex zona107;
    Vertex zona108;
    Vertex zona109;
    Vertex zona110;
    Vertex zona111;
    Vertex zona112;
    Vertex zona113;
    Vertex zona114;
    Vertex zona115;
    Vertex zona116;
    Vertex zona117;
    Vertex zona118;
    Vertex zona119;
    Vertex zona120;
    Vertex zona121;
    Vertex zona122;
    Vertex zona123;
    Vertex zona124;
    Vertex zona125;
    Vertex zona126;
    
    Edge a,b,c;     //Archi entrata labirinto
    Edge d;         //Arco corridoio
    
    public Graph() {
        adjList = new HashMap<>();
        init();
    }

    private void init(){
        zona1 = new Vertex(1);
        zona2 = new Vertex(2);
        zona3 = new Vertex(3);
        zona4 = new Vertex(4);
        zona5 = new Vertex(5);
        zona6 = new Vertex(6);
        zona7 = new Vertex(7);
        zona8 = new Vertex(8);
        zona9 = new Vertex(9);
        zona10 = new Vertex(10);
        zona11 = new Vertex(11);
        zona12 = new Vertex(12);
        zona13 = new Vertex(13);
        zona14 = new Vertex(14);
        zona15 = new Vertex(15);
        zona16 = new Vertex(16);
        zona17 = new Vertex(17);
        zona18 = new Vertex(18);
        zona19 = new Vertex(19);
        zona20 = new Vertex(20);
        zona21 = new Vertex(21);
        zona22 = new Vertex(22);
        zona23 = new Vertex(23);
        zona24 = new Vertex(24);
        zona25 = new Vertex(25);
        zona26 = new Vertex(26);
        zona27 = new Vertex(27);
        zona28 = new Vertex(28);
        zona29 = new Vertex(29);
        zona30 = new Vertex(30);
        zona31 = new Vertex(31);
        zona32 = new Vertex(32);
        zona33 = new Vertex(33);
        zona34 = new Vertex(34);
        zona35 = new Vertex(35);
        zona36 = new Vertex(36);
        zona37 = new Vertex(37);
        zona38 = new Vertex(38);
        zona39 = new Vertex(39);
        zona40 = new Vertex(40);
        zona41 = new Vertex(41);
        zona42 = new Vertex(42);
        zona43 = new Vertex(43);
        zona44 = new Vertex(44);
        zona45 = new Vertex(45);
        zona46 = new Vertex(46);
        zona47 = new Vertex(47);
        zona48 = new Vertex(48);
        zona49 = new Vertex(49);
        zona50 = new Vertex(50);
        zona51 = new Vertex(51);
        zona52 = new Vertex(52);
        zona53 = new Vertex(53);
        zona54 = new Vertex(54);
        zona55 = new Vertex(55);
        zona56 = new Vertex(56);
        zona57 = new Vertex(57);
        zona58 = new Vertex(58);
        zona59 = new Vertex(59);
        zona60 = new Vertex(60);
        zona61 = new Vertex(61);
        zona62 = new Vertex(62);
        zona63 = new Vertex(63);
        zona64 = new Vertex(64);
        zona65 = new Vertex(65);
        zona66 = new Vertex(66);
        zona67 = new Vertex(67);
        zona68 = new Vertex(68);
        zona69 = new Vertex(69);
        zona70 = new Vertex(70);
        zona71 = new Vertex(71);
        zona72 = new Vertex(72);
        zona73 = new Vertex(73);
        zona74 = new Vertex(74);
        zona75 = new Vertex(75);
        zona76 = new Vertex(76);
        zona77 = new Vertex(77);
        zona78 = new Vertex(78);
        zona79 = new Vertex(79);
        zona80 = new Vertex(80);
        zona81 = new Vertex(81);
        zona82 = new Vertex(82);
        zona83 = new Vertex(83);
        zona84 = new Vertex(84);
        zona85 = new Vertex(85);
        zona86 = new Vertex(86);
        zona87 = new Vertex(87);
        zona88 = new Vertex(88);
        zona89 = new Vertex(89);
        zona90 = new Vertex(90);
        zona91 = new Vertex(91);
        zona92 = new Vertex(92);
        zona93 = new Vertex(93);
        zona94 = new Vertex(94);
        zona95 = new Vertex(95);
        zona96 = new Vertex(96);
        zona97 = new Vertex(97);
        zona98 = new Vertex(98);
        zona99 = new Vertex(99);
        zona100 = new Vertex(100);
        zona101 = new Vertex(101);
        zona102 = new Vertex(102);
        zona103 = new Vertex(103);
        zona104 = new Vertex(104);
        zona105 = new Vertex(105);
        zona106 = new Vertex(106);
        zona107 = new Vertex(107);
        zona108 = new Vertex(108);
        zona109 = new Vertex(109);
        zona110 = new Vertex(110);
        zona111 = new Vertex(111);
        zona112 = new Vertex(112);
        zona113 = new Vertex(113);
        zona114 = new Vertex(114);
        zona115 = new Vertex(115);
        zona116 = new Vertex(116);
        zona117 = new Vertex(117);
        zona118 = new Vertex(118);
        zona119 = new Vertex(119);
        zona120 = new Vertex(120);
        zona121 = new Vertex(121);
        zona122 = new Vertex(122);
        zona123 = new Vertex(123);
        zona124 = new Vertex(124);
        zona125 = new Vertex(125);
        zona126 = new Vertex(126);
        
        this.insert_vertex(zona1);
        this.insert_vertex(zona2);
        this.insert_vertex(zona3);
        this.insert_vertex(zona4);
        this.insert_vertex(zona5);
        this.insert_vertex(zona6);
        this.insert_vertex(zona7);
        this.insert_vertex(zona8);
        this.insert_vertex(zona9);
        this.insert_vertex(zona10);
        this.insert_vertex(zona11);
        this.insert_vertex(zona12);     
        this.insert_vertex(zona13);
        this.insert_vertex(zona14);
        this.insert_vertex(zona15);
        this.insert_vertex(zona16);
        this.insert_vertex(zona17);
        this.insert_vertex(zona18);
        this.insert_vertex(zona19);
        this.insert_vertex(zona20);
        this.insert_vertex(zona21);
        this.insert_vertex(zona22);
        this.insert_vertex(zona23);
        this.insert_vertex(zona24);   
        this.insert_vertex(zona25);
        this.insert_vertex(zona26);
        this.insert_vertex(zona27);
        this.insert_vertex(zona28);
        this.insert_vertex(zona29);
        this.insert_vertex(zona30);
        this.insert_vertex(zona31);
        this.insert_vertex(zona32);
        this.insert_vertex(zona33);
        this.insert_vertex(zona34);
        this.insert_vertex(zona35);
        this.insert_vertex(zona36);     
        this.insert_vertex(zona37);
        this.insert_vertex(zona38);
        this.insert_vertex(zona39);
        this.insert_vertex(zona40);
        this.insert_vertex(zona41);
        this.insert_vertex(zona42);
        this.insert_vertex(zona43);
        this.insert_vertex(zona44);
        this.insert_vertex(zona45);
        this.insert_vertex(zona46);
        this.insert_vertex(zona47);
        this.insert_vertex(zona48);    
        this.insert_vertex(zona49);
        this.insert_vertex(zona50);     
        this.insert_vertex(zona51);
        this.insert_vertex(zona52);
        this.insert_vertex(zona53);
        this.insert_vertex(zona54);
        this.insert_vertex(zona55);
        this.insert_vertex(zona56);
        this.insert_vertex(zona57);
        this.insert_vertex(zona58);
        this.insert_vertex(zona59);
        this.insert_vertex(zona60);
        this.insert_vertex(zona61);
        this.insert_vertex(zona62); 
        this.insert_vertex(zona63);
        this.insert_vertex(zona64);
        this.insert_vertex(zona65);
        this.insert_vertex(zona66);
        this.insert_vertex(zona67);
        this.insert_vertex(zona68);
        this.insert_vertex(zona69);
        this.insert_vertex(zona70);
        this.insert_vertex(zona71);
        this.insert_vertex(zona72);
        this.insert_vertex(zona73);
        this.insert_vertex(zona74);
        this.insert_vertex(zona75);
        this.insert_vertex(zona76);
        this.insert_vertex(zona77);
        this.insert_vertex(zona78);
        this.insert_vertex(zona79);
        this.insert_vertex(zona80);
        this.insert_vertex(zona81);
        this.insert_vertex(zona82);
        this.insert_vertex(zona83);
        this.insert_vertex(zona84);
        this.insert_vertex(zona85);
        this.insert_vertex(zona86);
        this.insert_vertex(zona87); 
        this.insert_vertex(zona88);
        this.insert_vertex(zona89);
        this.insert_vertex(zona90);
        this.insert_vertex(zona91);
        this.insert_vertex(zona92);
        this.insert_vertex(zona93);
        this.insert_vertex(zona94);
        this.insert_vertex(zona95);
        this.insert_vertex(zona96);
        this.insert_vertex(zona97);
        this.insert_vertex(zona98);
        this.insert_vertex(zona99);
        this.insert_vertex(zona100);
        this.insert_vertex(zona101);
        this.insert_vertex(zona102);
        this.insert_vertex(zona103);
        this.insert_vertex(zona104);
        this.insert_vertex(zona105);
        this.insert_vertex(zona106);
        this.insert_vertex(zona107);
        this.insert_vertex(zona108);
        this.insert_vertex(zona109);
        this.insert_vertex(zona110);
        this.insert_vertex(zona111);
        this.insert_vertex(zona112);
        this.insert_vertex(zona113);
        this.insert_vertex(zona114);
        this.insert_vertex(zona115);
        this.insert_vertex(zona116);
        this.insert_vertex(zona117);
        this.insert_vertex(zona118);
        this.insert_vertex(zona119);
        this.insert_vertex(zona120);
        this.insert_vertex(zona121);
        this.insert_vertex(zona122);
        this.insert_vertex(zona123);
        this.insert_vertex(zona124);
        this.insert_vertex(zona125);
        this.insert_vertex(zona126);
        
        this.insert_edge(zona1, zona2, 0);
        this.insert_edge(zona1, zona15, 0);
        this.insert_edge(zona1, zona64, 0);
        
        this.insert_edge(zona2, zona3, 0);
        this.insert_edge(zona2, zona5, 0);
        
        this.insert_edge(zona3, zona10, 0);
        this.insert_edge(zona3, zona13, 0);
        this.insert_edge(zona3, zona65, 0);
        
        this.insert_edge(zona4, zona65, 0);
        this.insert_edge(zona4, zona66, 0);
        this.insert_edge(zona4, zona19, 0);
        
        this.insert_edge(zona5, zona6, 0);
        this.insert_edge(zona5, zona72, 0);
        
        this.insert_edge(zona6, zona7, 0);
        
        this.insert_edge(zona7, zona8, 0);
        
        this.insert_edge(zona8, zona9, 0);
        
        this.insert_edge(zona9, zona11, 0);
        this.insert_edge(zona9, zona10, 0);
        this.insert_edge(zona9, zona72, 0);
        
        this.insert_edge(zona10, zona13, 0);
        this.insert_edge(zona10, zona36, 0);
        this.insert_edge(zona10, zona67, 0);
        
        this.insert_edge(zona11, zona25, 0);
        this.insert_edge(zona11, zona27, 0);
        
        this.insert_edge(zona12, zona64, 0);
        this.insert_edge(zona12, zona14, 0);
        this.insert_edge(zona12, zona71, 0);
        
        this.insert_edge(zona13, zona67, 0);
        
        this.insert_edge(zona14, zona64, 0);
        
        this.insert_edge(zona15, zona16, 0);
        
        this.insert_edge(zona16, zona17, 0);
        
        this.insert_edge(zona17, zona18, 0);
        
        this.insert_edge(zona18, zona28, 0);
        
        this.insert_edge(zona19, zona20, 0);
        
        this.insert_edge(zona20, zona21, 0);
        
        this.insert_edge(zona21, zona66, 0);
        
        d = this.insert_edge(zona22, zona67, 0);
        this.insert_edge(zona22, zona30, 0);
        this.insert_edge(zona22, zona34, 0);
        this.insert_edge(zona22, zona29, 0);
        
        this.insert_edge(zona23, zona66, 0);
        this.insert_edge(zona23, zona67, 0);
        this.insert_edge(zona23, zona24, 0);
        
        this.insert_edge(zona24, zona69, 0);
        this.insert_edge(zona24, zona38, 0);
        
        this.insert_edge(zona25, zona26, 0);
        
        this.insert_edge(zona26, zona27, 0);
        this.insert_edge(zona26, zona28, 0);
        
        this.insert_edge(zona28, zona71, 0);
        
        b = this.insert_edge(zona29, zona85, 0);
        this.insert_edge(zona29, zona69, 0);
        this.insert_edge(zona29, zona70, 0);
        
        this.insert_edge(zona30, zona31, 0);
        
        this.insert_edge(zona31, zona33, 0);
        
        this.insert_edge(zona32, zona33, 0);
        
        this.insert_edge(zona33, zona69, 0);
        
        this.insert_edge(zona34, zona35, 0);
        
        this.insert_edge(zona35, zona70, 0);
        
        this.insert_edge(zona36, zona67, 0);
        this.insert_edge(zona36, zona68, 0);
        this.insert_edge(zona36, zona42, 0);
        
        this.insert_edge(zona37, zona68, 0);
        this.insert_edge(zona37, zona70, 0);
        this.insert_edge(zona37, zona41, 0);
        this.insert_edge(zona37, zona45, 0);
        this.insert_edge(zona37, zona63, 0);
        
        this.insert_edge(zona38, zona39, 0);

        this.insert_edge(zona39, zona109, 0);
        
//        this.insert_edge(zona40, zona89, 0);
        this.insert_edge(zona40, zona123, 0);
        this.insert_edge(zona40, zona124, 0);
        
        a = this.insert_edge(zona41, zona102, 0);
        this.insert_edge(zona41, zona118, 0);
        
        this.insert_edge(zona42, zona43, 0);
        this.insert_edge(zona42, zona73, 0);
        this.insert_edge(zona42, zona68, 0);
        
        this.insert_edge(zona43, zona68, 0);
        this.insert_edge(zona43, zona44, 0);
        
        this.insert_edge(zona44, zona45, 0);
        this.insert_edge(zona44, zona49, 0);
        this.insert_edge(zona44, zona47, 0);
        
        this.insert_edge(zona45, zona49, 0);
        this.insert_edge(zona45, zona63, 0);
        
        this.insert_edge(zona46, zona119, 0);
        this.insert_edge(zona46, zona48, 0);
        this.insert_edge(zona46, zona47, 0);
        this.insert_edge(zona46, zona52, 0);
        this.insert_edge(zona46, zona49, 0);
        
        this.insert_edge(zona47, zona48, 0);
        this.insert_edge(zona47, zona49, 0);
        
        this.insert_edge(zona48, zona73, 0);
        
        this.insert_edge(zona49, zona50, 0);
        this.insert_edge(zona49, zona51, 0);
        
        this.insert_edge(zona50, zona51, 0);
        this.insert_edge(zona50, zona52, 0);
        
        this.insert_edge(zona51, zona63, 0);
        this.insert_edge(zona51, zona58, 0);
        
        this.insert_edge(zona52, zona74, 0);
        
        this.insert_edge(zona53, zona74, 0);
        this.insert_edge(zona53, zona54, 0);
        
        this.insert_edge(zona54, zona75, 0);
        this.insert_edge(zona54, zona116, 0);
        this.insert_edge(zona54, zona114, 0);
        
        this.insert_edge(zona55, zona74, 0);
        this.insert_edge(zona55, zona56, 0);
        this.insert_edge(zona55, zona57, 0);
        
        //56
        
        this.insert_edge(zona57, zona59, 0);
        this.insert_edge(zona57, zona108, 0);
        
        this.insert_edge(zona58, zona108, 0);
        
        this.insert_edge(zona59, zona108, 0);
        this.insert_edge(zona59, zona126, 0);
        
        this.insert_edge(zona60, zona61, 0);
        this.insert_edge(zona60, zona62, 0);        
        this.insert_edge(zona60, zona118, 0);

        
        //61
        
        this.insert_edge(zona62, zona117, 0);
        
        //63
        
        //64
        
        //65
        
        //66
        
        //67
        
        //68
        
        //69
        
        //70
        
        this.insert_edge(zona71, zona119, 0);
        
        //72

        //73
        
        //74
        
        this.insert_edge(zona75, zona115, 0);
        
        this.insert_edge(zona76, zona109, 0);
        this.insert_edge(zona76, zona118, 0);
        this.insert_edge(zona76, zona126, 0);
        
        this.insert_edge(zona77, zona117, 0);
        
        this.insert_edge(zona78, zona120, 0);
        b = this.insert_edge(zona29, zona85, 0);
        this.insert_edge(zona78, zona121, 0);
        
        this.insert_edge(zona79, zona80, 0);
        this.insert_edge(zona79, zona120, 0);
        this.insert_edge(zona80, zona81, 0);
        
        this.insert_edge(zona81, zona82, 0);
        
        this.insert_edge(zona82, zona83, 0);
        this.insert_edge(zona82, zona84, 0);
        
        this.insert_edge(zona83, zona85, 0);
        
        this.insert_edge(zona84, zona110, 0);
        
        this.insert_edge(zona85, zona89, 0);
        
        this.insert_edge(zona86, zona87, 0);
        this.insert_edge(zona86, zona110, 0);
        this.insert_edge(zona86, zona121, 0);
        
        this.insert_edge(zona87, zona90, 0);
        this.insert_edge(zona87, zona124, 0);
        
        this.insert_edge(zona88, zona95, 0);
        this.insert_edge(zona88, zona98, 0);
        this.insert_edge(zona88, zona125, 0);
        
        this.insert_edge(zona89, zona123, 0);
        
        this.insert_edge(zona90, zona112, 0);
        
        this.insert_edge(zona91, zona112, 0);
        this.insert_edge(zona91, zona92, 0);
        this.insert_edge(zona91, zona93, 0);
        
        // 92
        
        this.insert_edge(zona93, zona94, 0);
        this.insert_edge(zona93, zona122, 0);
        
        this.insert_edge(zona94, zona95, 0);
        
        this.insert_edge(zona95, zona96, 0);
        
        this.insert_edge(zona96, zona97, 0);
        
        this.insert_edge(zona97,zona111, 0);
        
        this.insert_edge(zona98, zona99, 0);
        this.insert_edge(zona98, zona101, 0);
        
        this.insert_edge(zona99, zona100, 0);
        
        this.insert_edge(zona100, zona101, 0);
        
        this.insert_edge(zona101, zona102, 0);
        
        this.insert_edge(zona102, zona103, 0);
        
        this.insert_edge(zona103, zona104, 0);
        
        this.insert_edge(zona104, zona106, 0);
        
        this.insert_edge(zona105, zona107, 0);
        this.insert_edge(zona105, zona122, 0);
        
        this.insert_edge(zona106, zona107, 0);
        
        // 107
        
        // 108
        
        // 109
        
        // 110
        
        this.insert_edge(zona111, zona123, 0);
        
        // 112
        
        this.insert_edge(zona113, zona116, 0);
        
        // 114
        
        // 115
        
        // 116
        
        // 117
        
       this.insert_edge(zona111, zona118, 0);
        
        // 119
        
        // 120
        
        // 121
        
        // 122
        
        // 123
        
        // 124
        
        // 125
        
        // 126
    }
    public int vertex_count(){
        return adjList.size();
    }
    
    public LinkedList<Vertex> vertices(){
        LinkedList<Vertex> vertices = new LinkedList();
        for (Vertex s: adjList.keySet())
            vertices.add(s);
        return vertices;
    }
    
    public int edge_count(){
        int c=0;
        for (Map s: adjList.values())
            c += s.size();
        return c;
    }
    
    public Set edges(){
        Set result = new HashSet();
        for (Map s: adjList.values())
            result.add(s.values());
        return result;
    }
    
    public Edge get_edge(Vertex origin, Vertex destination){
        if(adjList.containsKey(origin) && adjList.containsKey(destination))
            return adjList.get(origin).get(destination);
        else
            return null;
    }
    
    public static LinkedList<Edge> incident_edges(Vertex v){
        LinkedList<Edge> edges = new LinkedList();
        if(adjList.containsKey(v)){
            for(Edge e: adjList.get(v).values())
                edges.add(e);
            return edges;
        }else{
            System.out.println("problema incident_edges");
            return null;
        }
    }
    
    public Vertex insert_vertex(Vertex v){
        adjList.put(v, new HashMap<>());
        return v;
    }
    
    public Edge insert_edge(Vertex origin, Vertex destination, int traffic){
        if(adjList.containsKey(origin) && adjList.containsKey(destination)){
            Edge e = new Edge(origin, destination, traffic);
            adjList.get(origin).put(destination, e);
            adjList.get(destination).put(origin, e);
            return e;
        }else{
            System.out.println("problema insert_edge");
            return null;
        }
        
    }
    
    public void remove_edge(Edge e){
        Vertex u = e.origin;
        Vertex v = e.destination;
        
        adjList.get(u).remove(v);
        adjList.get(v).remove(u);
    }
    
    public void stampa(){
        for (Vertex v: adjList.keySet()){
            System.out.println("map fuori: "+v);
            Map<Vertex,Edge> map2 = adjList.get(v);
            for (Vertex v2: map2.keySet()){
                System.out.println("map dentro: "+v2);
                System.out.println(map2.get(v2));
            }
        }
    }
    
    public static Map<Vertex,Edge> BFS_complete(Vertex u){
        if(adjList.containsKey(u)){
            Map<Vertex,Edge> forest = new HashMap();
            forest.put(u, null);
            bfs(u,forest);
            return forest;
        }else{
            System.out.println("Errore nella bfs_complete");
            return null;
        }
    }
    
    public static void bfs(Vertex s, Map<Vertex,Edge> forest){
        ArrayList<Vertex> level = new ArrayList();
        level.add(s);
        while(level.size()>0){
            ArrayList<Vertex> nextLevel = new ArrayList();
            for(Vertex u: level){
                for(Edge e: incident_edges(u)){
                    Vertex v = e.opposite(u);
                    if(! forest.containsKey(v)){
                        forest.put(v, e);
                        nextLevel.add(v);
                    }
                }
            }
            level = nextLevel;
        }
    }
    
    public void rimuoviEntrataLabirinto(){
        this.remove_edge(a);
        this.remove_edge(b);
        this.remove_edge(c);
    }
    
    public void inserisciEntrataLabirinto(){
        a = this.insert_edge(zona41, zona102, 0);
        b = this.insert_edge(zona29, zona85, 0);
        c = this.insert_edge(zona29, zona85, 0);
    }
    
    public void rimuoviCorridoio(){
        this.remove_edge(d);
    }
    
    public void inserisciCorridoio(){
        d = this.insert_edge(zona22, zona67, 0);
    }
}
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
public class Vertex {
    private int index_zona;

        public Vertex(int index_zona) {
            this.index_zona = index_zona;
        }

        @Override
        public boolean equals(Object obj){
            if (this == obj)
                return true;
            if (!(obj instanceof Vertex)) 
                return false;
            
            Vertex _obj = (Vertex) obj;
            return _obj.index_zona==this.index_zona;
        }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.index_zona;
        return hash;
    }

        public int getElement() {
            return this.index_zona;
        }

        public void setZona(int index_zona) {
            this.index_zona = index_zona;
        }
        
    @Override
        public String toString(){
            return "Vertice: " + index_zona;
        }
    }

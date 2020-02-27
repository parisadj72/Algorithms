/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minheap;

import java.util.Vector;


/**
 *
 * @author Asus K45v
 */
class MinHeap{
    private Vector v;
    public MinHeap(){
        v=new Vector();
    }
    public void insert(Comparable c){
        v.insertElementAt(c, v.size());
        int j=v.size();
        while((j-1)/2>=0){
            if(((Comparable)v.elementAt(j)).compareTo(((Comparable)v.elementAt((j-1)/2)))==-1){
                Object a=v.elementAt((j-1)/2);
                v.setElementAt(v.elementAt(j), (j-1)/2);
                v.setElementAt(a, j);
                j=(j-1)/2;
            }
            else{
                return;
            }
        }
    }
    public void RemoveMin(){
        
    }
}
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}

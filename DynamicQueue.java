/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dynamicqueue;

import java.util.LinkedList;

/**
 *
 * @author Asus K45v
 */
public class DynamicQueue {

    /**
     * @param args the command line arguments
     */
    public class DQueue{
        private LinkedList<Integer> l=new LinkedList <Integer> ();
        void enqueue(Object o){
            l.addLast((Integer) o);
        }
        Object dequeue()throws Exception{
            if(l.isEmpty())
                throw new Exception();
            return l.removeFirst();
        }
        Object Front(){
            return l.getFirst();
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
}

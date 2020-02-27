/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generaltree;

import java.util.LinkedList;

/**
 *
 * @author Asus K45v
 */
public class GeneralTree {

    private static interface Position {
        
    }

    /**
     * @param args the command line arguments
     */
    public class GTree{
        class TreeNode implements Position{
            private Object Element;
            private LinkedList<Integer> l=new LinkedList<Integer>();
            private  TreeNode parent;
            public TreeNode GetParent(){
                return parent;
            }
            public void SetParent(TreeNode t){
                parent=t;
            }
            public Object GetElement(){
                return Element;
            }
            public void SetElement(Object o){
                Element=o;
            }
        }
        
        public Position Root(){
            
        }
        public Position Parent(Position p){
            
        }
        public LinkedList Children(Position p){
            
        }
        public Position InsertRoot(Object o){
            
        }
        public Position CreatNode(Object o){
            
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
}

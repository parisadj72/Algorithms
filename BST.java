/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bintree;

import java.util.Comparator;

/**
 *
 * @author Parisa Daeijavad
 */


class Entry{
    Object key;
    Object value;
    public Entry(Object k,Object v){
        key=k;
        value=v;
    }
}
class BinTree{
    private Entry element;
    private BinTree left,right,parent;
    public BinTree GetParent(){
        return parent;
    }
    public void SetParent(BinTree t){
        parent=t;
    }
    public BinTree GetLeft(){
        return left;
    }
    public void SetLeft(BinTree t){
        left=t;
    }
    public BinTree GetRight(){
        return right;
    }
    public void SetRight(BinTree t){
        right=t;
    }
    public BinTree(){}
    public BinTree(Entry e,BinTree r,BinTree l){
        element=e;
        right=r;
        left=l;
    }
    public boolean hasleft(){
        return left!=null;
    }
    public boolean hasright(){
        return right!=null;
    }
    public boolean hasparent(){
        return parent!=null;
    }
    public Entry RetreiveRoot(){
        return element;
    }
    public void insertleft(Entry e){
        if(hasleft())
            return;
        BinTree t=new BinTree(e, null, null);
        left=t;
        t.parent=this;
    }
    public void insertright(Entry e){
        if(hasright())
            return;
        BinTree t=new BinTree(e, null, null);
        right=t;
        t.parent=this;
    }
    public void insertroot(Entry e){
        if(this.element!=null)
            return;
        BinTree t=new BinTree(e, null, null);
    }
    public Object remove(){
        Object tmp=element;
        if(hasleft()&&hasright())
            return null;
        else if(!hasleft()&&!hasright()){
            element = null;
            if(hasparent()){
                if(parent.left==this){
                    parent.left=null;
                }
                else{
                    parent.right=null;
                }
            }
            return tmp;
        }
        else if(hasright()){
            element = null;
            if(hasparent()){
                if(parent.left==this){
                    parent.left=right;
                    right.parent=parent;
                    return tmp;
                }
                else{
                    parent.right=right;
                    right.parent=parent;
                    return tmp;
                }
            }
            else{
                BinTree t2=right;
                left=t2.left;
                right=t2.right;
                element=t2.element;
                if(t2.hasleft())
                    t2.left.parent=this;
                if(t2.hasright())
                    t2.right.parent=this;
                return tmp;
            }
        }
        else{
            element = null;
            if(hasparent()){
                if(parent.left==this){
                    parent.left=left;
                    left.parent=parent;
                    return tmp;
                }
                else{
                    parent.right=left;
                    left.parent=parent;
                    return tmp;
                }
            }
            else{
                BinTree t2=left;
                left=t2.left;
                right=t2.right;
                element=t2.element;
                if(t2.hasleft())
                    t2.left.parent=this;
                if(t2.hasright())
                    t2.right.parent=this;
                return tmp;
            }
        }
    }
    public boolean isEmpty(){
        return element==null;
    }
}

class BST extends BinTree{
    private Comparator c;
    private BinTree t;
    public BST(Comparator c){
        t=new BinTree();
        this.c=c;
    }
    private Object LocalSearch(Object k, BinTree t){
        if(t==null) 
            return null;
        if(c.compare(k, t.RetreiveRoot().key)==0)
            return t.RetreiveRoot().value;
        else if(c.compare(k, t.RetreiveRoot().key)==-1){
            return LocalSearch(k, t.GetLeft());
        }
        else{
            return LocalSearch(k, t.GetRight());
        }
    }
    public Object Search(Object k){
        if(t.isEmpty())
            return null;
        return LocalSearch(k,t);
    }
    public void insert(Entry e){
        BinTree t2=t;
        if(t.isEmpty()){
            t.insertroot(e);
            return;
        }
        while(true){
           if(c.compare(e.key, t2.RetreiveRoot().key)==0){
               t2.RetreiveRoot().value=e.value;
               break;
           }
           else if(c.compare(e.key, t2.RetreiveRoot().key)==-1){
               if(t2.hasleft())
                   t2=t2.GetLeft();
               else{
                   t2.insertleft(e);
                   break;
               }
           }
           else{
               if(t2.hasright())
                   t2=t2.GetRight();
               else{
                   t2.insertright(e);
                   break;
               }
           }
        }
    }
    public void remove(Object k){
    BinTree t2=t;
    while(t2!=null){
        if(c.compare(k, t.RetreiveRoot().key)==0){
            BinTree tmp=t;
            tmp=tmp.GetLeft();
            while(tmp.hasright())
                tmp=tmp.GetRight();
            t2.RetreiveRoot().key=tmp.RetreiveRoot().key;
            t2.RetreiveRoot().value=tmp.RetreiveRoot().value;
            tmp.remove();
            return;
        }
        else if(c.compare(k, t.RetreiveRoot().key)==-1){
            t2=t2.GetLeft();
        }
        else{
            t2=t2.GetRight();
        }
    }
    }
}

public class MyClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Comparator c = null;
        BST s=new BST(c);
        Entry e=new Entry(11, 110);
        s.insert(e);
        e=new Entry(8, 80);
        s.insert(e);
        e=new Entry(9, 90);
        s.insert(e);
        e=new Entry(12, 120);
        s.insert(e);
        System.out.println(s.Search(12));
        // TODO code application logic here
    }
}

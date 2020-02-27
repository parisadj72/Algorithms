/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PolynomialNode;

import java.util.Scanner;
import static PolynomialNode.PolynomialNode.stoi;
import static PolynomialNode.PolynomialNode.z;


/**
 *
 * @author Asus K45v
 */
    class TermNode { 
        private char Data;
        private int pow;
        private TermNode next;
        private TermNode prev;
        public TermNode(TermNode p,TermNode n,char c ,int a){
            Data=c;
            pow=a;
            next=n;
            prev=p;
        }
        public char GetData(){
            return Data;
        }
        public void SetData(char c){
            Data=c;
        }
        public int GetPow(){
            return pow;
        }
        public void SetPow(int i){
            pow=i;
        }
        public TermNode GetNext(){
            return next;
        }
        public void SetNext(TermNode n){
            next=n;
        }
        public TermNode GetPrev(){
            return prev;
        }
        public void SetPrev(TermNode p){
                prev=p;
            }
        }
    interface MT{
        public boolean isEmpty();
        public void insert(char c, int a);
    }
    class MyTerm implements MT{
        private double coef;
        private int size;
        private TermNode head;
        private TermNode last;
        @Override
        public boolean isEmpty(){
            if(size==0)return true;
            return false;
        }
        public TermNode last(){
            return last;
        }
        public TermNode head(){
            return head;
        }
        public MyTerm(){ 
            size=0;
        }
        public double GetCoef(){
            return coef;
        }
        public void SetCoef(double n){
            coef=n;
        }
        public int Getsize(){
            return size;
        }
        @Override
        public void insert(char c, int a){
            TermNode tmp=new TermNode(null , null, c, a);
            if(isEmpty()) {
                last=head=tmp;
                size=size+1;
                return;
            }
            TermNode ptr=head;
            while(ptr!=null){
                if(tmp.GetData()<ptr.GetData()){
                    
                    if(ptr==head){
                        tmp.SetNext(ptr);
                        ptr.SetPrev(tmp);
                        head=tmp;
                    }
                    else{
                        tmp.SetNext(ptr);
                        tmp.SetPrev(ptr.GetPrev());
                        ptr.GetPrev().SetNext(tmp);
                        ptr.SetPrev(tmp);
                    }
                    size=size+1;
                    return;
                }
                else if(tmp.GetData()==ptr.GetData()){
                    ptr.SetPow(tmp.GetPow()+ptr.GetPow());
                    return;
                }
                else{
                    if(ptr.GetNext()==null){
                        tmp.SetNext(null);
                        tmp.SetPrev(ptr);
                        ptr.SetNext(tmp);
                        last=tmp;
                        size=size+1;
                        return;
                    }
                    else{
                        ptr=ptr.GetNext();
                    }
                }
            }
        }
    }
    class PolyNode { 
        private MyTerm T;
        private PolyNode next;
        private PolyNode prev;
        
        public PolyNode(){}
        public PolyNode(PolyNode p,PolyNode n,MyTerm A){
            T=A;
            next=n;
            prev=p;
        }
        public PolyNode(MyTerm A){
            T=A;
        }
        public PolyNode(MyTerm M, PolyNode head, PolyNode last) {
            T=M;
            next=head;
            prev=last;
        }
        public PolyNode GetNext(){
            return next;
        }
        public void SetNext(PolyNode n){
            next=n;
        }
        public PolyNode GetPrev(){
            return prev;
        }
        public void SetPrev(PolyNode p){
            prev=p;
        }
        public MyTerm GetT(){
            return T;
        }
        public void SetT(MyTerm m){
            T=m;
        }
        public PolyNode Muliply(PolyNode tmp){
            PolyNode ptr=new PolyNode(tmp.T, null, null);
            ptr.GetT().SetCoef(tmp.GetT().GetCoef()*this.GetT().GetCoef());
            TermNode t=this.GetT().head();
            while(t!=null){
                ptr.GetT().insert(t.GetData(), t.GetPow());
                t=t.GetNext();
            }
            return ptr;
        }
    }
    interface PL{
        public boolean isEmpty();
//        public void remove(PolyNode n);
        public void insertlast(MyTerm A);
//        public Poly SortPoly(); 
        public Poly ReadPoly(String n); 
        public String WritePoly(); 
        public Poly AddPoly(Poly a); 
        public Poly SubtractPoly(Poly a); 
        public Poly MuliplyPoly(Poly a);  
    }
    class Poly implements PL{
        private int size;
        private PolyNode head;
        private PolyNode last;
        public PolyNode last(){
            return last;
        }
        public PolyNode head(){
            return head;
        }
        public Poly(){size=0; }
        @Override
        public boolean isEmpty(){
            if(size==0)return true;
            return false;
        }
        @Override
        public void insertlast(MyTerm A){
            if(A.GetCoef()==0)
                return;
            PolyNode tmp=new PolyNode(A);
            if(isEmpty()) last=head=tmp;
            else{
                TermNode ptr1;
                PolyNode tmp2=head;
                TermNode ptr2;
                while(tmp2!=null){
                        ptr2=tmp2.GetT().head();
                        ptr1=tmp.GetT().head();
                        if(tmp.GetT().Getsize()==tmp2.GetT().Getsize()){
                            if(ptr1==null){
                                tmp2.GetT().SetCoef(tmp.GetT().GetCoef()+tmp2.GetT().GetCoef());
                                return;
                            }
                            else{
                                while(ptr1!=null && ptr1.GetData()==ptr2.GetData() && ptr1.GetPow()==ptr2.GetPow()){
                                    ptr1=ptr1.GetNext();
                                    ptr2=ptr2.GetNext();
                                }
                                if(ptr1==null){
                                    tmp2.GetT().SetCoef(tmp.GetT().GetCoef()+tmp2.GetT().GetCoef());
                                    return;
                                }
                            }
                        }
                        tmp2=tmp2.GetNext();
                    }
                tmp.SetNext(null);
                tmp.SetPrev(last);
                last.SetNext(tmp);
                last=tmp;
            }
            size++;
        }
        @Override
        public Poly ReadPoly(String n){          
            char[] c=n.toCharArray();            
            Poly p = new Poly();
            int g=0;
            for(int i=0;i<c.length;i++) 
            {
                if((c[i]=='+' || c[i]=='-')&& i!=0)
                {
                    String S1=n.substring(g,i);
                    MyTerm term=new MyTerm();
                    term.SetCoef(stoi(S1));
                    g=z;
                    S1=S1.substring(g,S1.length());
                    
                    char[] C1=S1.toCharArray();
                    int f=0;
                    int b;
                    while(f!=C1.length)
                    {
                        if(f==C1.length-1)
                        {
                            term.insert(C1[f], 1);
                            f++;
                        }
                        else if(C1[f+1]=='^'){
                            String S2=S1;
//                            System.out.println(S2);
                            S2=S2.substring(f+2,S2.length());
//                            System.out.println(S2);
                            char[] ch=S2.toCharArray();
                            int in=0;
                            while(ch[in]=='.' || (ch[in]-48>=0 && ch[in]-48<=9)){
                                in++;
                                if(in==ch.length)
                                    break;
                            }
                            S2=S2.substring(0, in);
                            b=Integer.parseInt(S2);
                            term.insert(C1[f], b);
                            int t=0;
                            while(b>0){
                                b=b/10;
                                t++;
                            }
                            f=f+t+2;
                        }
                        else{
                            term.insert(C1[f], 1);
                            f++;
                        }  
                    }
                    p.insertlast(term);
                    g=i;
                }
            }
            String S1=n.substring(g,n.length());
            MyTerm array=new MyTerm();
            array.SetCoef(stoi(S1));
            g=z;
            S1=S1.substring(g,S1.length());
                    
            char[] C2=S1.toCharArray();
            int f=0;
            int b;
            while(f!=C2.length){
                if(f==C2.length-1){
                    array.insert(C2[f], 1);
                    f++;
                }
                else if(C2[f+1]=='^'){
                        String S2=S1;
//                        System.out.println(S2);
                        S2=S2.substring(f+2,S1.length());
//                        System.out.println(S2);
                        char[] ch=S2.toCharArray();
                        int in=0;
                        while(ch[in]=='.' || (ch[in]-48>=0 && ch[in]-48<=9)){
                            in++;
                            if(in==ch.length)
                                break;
                        }
                        S2=S2.substring(0, in);
                        b=Integer.parseInt(S2);
                        array.insert(C2[f], b);
                        int t=0;
                        while(b>0){
                            b=b/10;
                            t++;
                        }
                        f=f+t+2;
                    }
                else{
                    array.insert(C2[f], 1);
                    f++;
                } 
            }
            p.insertlast(array);
            return p;
        }
        @Override
        public String WritePoly(){
            String a="";
            if(this.head==null){
//                System.out.println("0");
                a="0";
            }
            PolyNode tmp=head;
            while(tmp!=null){
                if(tmp.GetT().GetCoef()!=1 && tmp.GetT().GetCoef()!=0)
//                    System.out.print(tmp.GetT().GetCoef());
                    a+=Double.toString(tmp.GetT().GetCoef());
                TermNode ptr=tmp.GetT().head();
                if(tmp.GetT().GetCoef()!=0){
                    while(ptr!=null){
//                        System.out.print(ptr.GetData());
                        a+=Character.toString(ptr.GetData());
                        if(ptr.GetPow()!=1){
//                            System.out.print('^');
                            a+="^";
//                            System.out.print(ptr.GetPow());
                            a+=Integer.toString(ptr.GetPow());
                        }
                    ptr=ptr.GetNext();
                    }
                }
                tmp=tmp.GetNext();
                if(tmp!=null && tmp.GetT().GetCoef()==0){
                    tmp=tmp.GetNext();
                }
                if(tmp!=null && tmp.GetT().GetCoef()>0){
//                    System.out.print('+');
                    a+="+";
                }
                
            }
//            System.out.println(" ");
            return a;
        }
        @Override
        public Poly AddPoly(Poly a){
            Poly result=new Poly();
            PolyNode tmp=a.head;
            while(tmp!=null){
                result.insertlast(tmp.GetT());
                tmp=tmp.GetNext();
            }
            tmp=this.head;
            while(tmp!=null){
                result.insertlast(tmp.GetT());
                tmp=tmp.GetNext();
            }
            return result;
        } 
        @Override
        public Poly SubtractPoly(Poly a){
            PolyNode temp=a.head;
            while(temp!=null){
                double d=temp.GetT().GetCoef()*(-1);
                temp.GetT().SetCoef(d);
                temp=temp.GetNext();
            }
            Poly result=new Poly();
            PolyNode tmp=a.head;
            while(tmp!=null){
                result.insertlast(tmp.GetT());
                tmp=tmp.GetNext();
            }
            tmp=this.head;
            while(tmp!=null){
                result.insertlast(tmp.GetT());
                tmp=tmp.GetNext();
            }
            return result;
        } 
        @Override
        public Poly MuliplyPoly(Poly a){
            String s=this.WritePoly();
            String ss=a.WritePoly();
            
            Poly muli=new Poly();
            PolyNode tmp1=this.head;
            PolyNode tmp2;
            while(tmp1!=null){
                tmp2=a.ReadPoly(ss).head;
                while(tmp2!=null){
                    PolyNode ptr=tmp1.Muliply(tmp2);
                    muli.insertlast(ptr.GetT());
                    tmp2=tmp2.GetNext();
                }
                tmp1=tmp1.GetNext();
            }
            return muli;
        }  
    }
//3x^2y^13+3x^2y^2z+11
//31x^2y^2z+3x^13y^2+x
public class PolynomialNode{

    /**
     * @param args the command line arguments
     */
    public static int z=0;
    public static double stoi (String d)
    {
        z=0;
        String s=d;
       
        char o='+';
        if(s.startsWith("-"))
        {
            z++;
            o='-';
        }
        if(s.startsWith("+"))
        {
            z++;
            o='+';
        }
        char[] c=s.toCharArray();
        while(c[z]=='.' || (c[z]-48>=0 && c[z]-48<=9)){
            z++;
            if(z==c.length)
                break;
        }
        s=s.substring(0,z);
        if("+".equals(s))
            s+="1";
        if("-".equals(s))
            s+="1";
        if("".equals(s))
            s+="1";
        
        double st;
        st=Double.parseDouble(s);
        return st;
    }

    
    public static void main(String[] args) {
            // TODO code application logic here

            System.out.println("\nPlease enter first Polynomial : ");
            Scanner s1=new Scanner(System.in);
            String n=s1.nextLine();

            System.out.println("\nPlease enter secound Polynomial : ");
            Scanner s2=new Scanner(System.in);
            String m=s2.nextLine();
        
//        try {
//            File f=new File("file.txt");
//            String n,m,o;
//            if (!f.exists()){
//                f.createNewFile();
//            }
//            Scanner scan=new Scanner(f);
//            if(scan.hasNextLine())
//            {
//                n=scan.nextLine();
//                m=scan.nextLine();
//                o=scan.nextLine();
//            }
//
//        }
//        catch (Exception ex) { }
        
//        String n="2x^2y-ot^56+gw+4ot^56";
//        String m="3ot^56";
//        String o="*";

            Poly p1=new Poly();
            Poly p2=new Poly();

            p1=p1.ReadPoly(n);
            p2=p2.ReadPoly(m);

            System.out.println("\nPlease enter the Operator : ");
            Scanner s3=new Scanner(System.in);
            String o=s3.nextLine();

            Poly result=new Poly();
            
            if(o.equals("+")){
                result=p1.AddPoly(p2);
            }
            else if(o.equals("-")){
                result=p1.SubtractPoly(p2);
            }
            else if(o.equals("*")){
                result=p1.MuliplyPoly(p2);
            }
            System.out.println(result.WritePoly());
        }
    }
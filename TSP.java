package TSP;
import java.util.*;
public class TSP {

       static int city=0;//city is a universal
       public static void main(String[] args) {
        Scanner in=new Scanner (System.in);
        System.out.print("Enter Number of city:");
        city=in.nextInt();
        int[][]N=new int[city+1][(int)Math.pow(2.0,(double)city)+1];
        int p=(int)Math.pow(2.0,  (double)city)+1;
        for(int i=0;i<city+1;i++)
            for(int j=0;j<p;j++)
                N[i][j]=0;
        int[][]N1=new int[city+1][(int)Math.pow(2.0,(double)city)+1];
        int [][]w=new int[(int)city+1][(int)city+1];
        int m[][]=new int[city+1][(int)Math.pow(2.0,(double)city)+1];
        for(int i=0;i<city+1;i++)
            for(int j=0;j<p;j++)
                m[i][j]=0;
        System.out.print("Enter weight of Edge:(if hasn't Edge Enter 1000"+")");
        for(int i=0;i<city;i++){
        for(int j=i;j<city;j++){
            if(i==j){
                w[i+1][j+1]=0;
            continue;
            }
            System.out.print("Enter w["+(i+1)+"]["+(j+1)+"]");
        w[i+1][j+1]=in.nextInt();
         System.out.print("Enter w["+(j+1)+"]["+(i+1)+"]");
        w[j+1][i+1]=in.nextInt();

    }
        }

          TSP(w,m,N,city);
          print(N, m, w);


 }
    private static int[] HToV(int h) {
      int vert[]=new int[city+1];
      String V="";
    V=Integer.toBinaryString(h);
    int len=V.length();
    if(len!=city){
        String s="0";
        int l=city-len;
        for(int k=0;k<l;k++)
         V= s.concat(V);
    }
    for(int i=0,j=0;i<city;i++)
    {
        if(V.charAt(i)=='1')
            vert[j++]=i+1;
    }
return vert;
    }
   private static void TSP(int[][] w, int[][] m, int[][] N, int n) {
       int arr[]=new int[(int)Math.pow(2.0, (double)n)];
       int c=0,t=0,t1=0;
       boolean test=true;
          int bine[]=new int[n+1];
        for(int i=1;i<=n;i++)
                m[i][0]=w[i][1];
          subset subset2=new subset();
          arr=subset2.combination(1,n);

           while(arr[t1]!=0){
                bine=HToV(arr[t1]);
          for(int i=2;i<=n;i++){
              if(i!=bine[0]){
              m[i][arr[t1]]=w[i][bine[0]]+w[bine[0]][1];
              N[i][arr[t1]]=bine[0];
              }
          }
          t1++;
           }
             for(int k=2;k<=n-2;k++){
                    arr=subset2.combination(k,n);
                        t=0;
                    while(arr[t]!=0){
                 for(int i=2,e=0;i<=n;i++)
                 {
                     test=true;
                  for(int i1=0;i1<=n;i1++)
                      bine[i1]=0;
                   bine=HToV(arr[t]);

                   c=0;
                   while(bine[c]!=0)
                   {
                       if(bine[c]==i){
                           test=false;
                           break;
                       }

                       c++;
                   }
                   if(test==true){
                       m[i][arr[t]]=( m[bine[0]][min(arr[t],bine[0])]+w[i][bine[0]]);
                       N[i][arr[t]]=bine[0];
                       for(int j=2;j<=k;j++)
                           if(m[i][arr[t]]>w[i][bine[j-1]]+m[bine[j-1]][min(arr[t],bine[j-1])]){
                               m[i][arr[t]]=w[i][bine[j-1]]+m[bine[j-1]][min(arr[t],bine[j-1])];
                               N[i][arr[t]]=bine[j-1];
                           }
                   }
                   else{
                       m[i][arr[t]]=-1;
                       N[i][arr[t]]=-1;
                   }

                 }
                 t++;
                    }
       }
         int f=(int)Math.pow(2.0, n)+1;
          for(int i=0;i<n+1;i++ ){
              for(int j=0;j<f;j++)
                  System.out.print(m[i][j]+"    ");
          System.out.println();
          }
         System.out.println();
         System.out.println();
          for(int i=0;i<n+1;i++ ){
              for(int j=0;j<f;j++)
                  System.out.print(N[i][j]+"    ");
                  System.out.println();
          }
         
    }


     private static int min (int A,int S){
         String temp1="";
         int r=0;
         String temp2="";
         int i=0,k=0;
         int bine[]=new int[city+1];
         for(int i1=0;i1<=city;i1++)
                bine[i1]=0;
                   bine=HToV(A);
         i=bine.length;
         while(i!=0){
             if(bine[k]==S){
                 bine[k]=0;
             }
             i--;
             k++;
         }
         Integer []t=new Integer[city+1];
         for(int j=0,e=0;j<bine.length;j++){
             if(bine[j]!=0)
                 t[e++]=bine[j];}
         subset k9=new subset();
         r=subset.strTOint(t,city);
      return r;
     }
       public static int MofV(int m[][] ,int w[][])
     {
           subset k1=new subset();
         int min_v =2;
         int f[]=k1.combination(city-1,city);
         int min;
         int m_t =w[1][2]+m[2][min(f[0] , 2 )];
         for(int i=3;i<=city;i++){
             if(w[1][i]+m[i][min(f[0],i)]<m_t)
             {
                 m_t=w[1][i] + m[i][min(f[0],i)];
                 min_v=i;
             }
         }
         return min_v;
     }
     public static void print(int N[][] , int m[][] , int w[][])
     {
         subset k2=new subset();
         int p[]=new int [city+1];
         p[0]=p[city]=1;
         p[1]=MofV(m,w);
         int f[]=k2.combination(city-1,city);
         int k=2;
         while(k<city)
         {
             int dg=f[0];
          
             for(int i=1;i<k;i++)
             {
                 dg= min(dg,p[i]);
              
             }
             p[k]= N[p[k-1]][dg];
             k++;

         }
          System.out.println("the path is:");
         for(int i=0;i<=city;i++ )
             System.out.print("v"+p[i]+" ");

     }
       }
       



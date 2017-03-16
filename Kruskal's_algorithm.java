/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemsolving;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Union;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;


/**
 *
 * @author darklife
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static class Edge implements Comparable<Edge>
    {
        public int src, dest, weight;

        public Edge(int s, int d, int w) {
            src= s;
            dest = d;
            weight = w;
        }
        
        @Override
        public int compareTo(Edge E)
        {
            return this.weight - E.weight;
        }
    }
    
    public static class Disjoint_Set
    {
        int [] parent, rank;
        int nNodes;

        public Disjoint_Set(int n) {
            parent = new int[n];
            rank = new int[n];
            nNodes = n ;
            
            for(int i = 0 ; i < n ; i++)
            {
                
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int findRep(int node)
        {
            if(parent[node] != node)
            {
                parent[node] = findRep(parent[node]);
            }
            
            return parent[node];
        }
        
        public boolean union(int x , int y)
        {
            
            int xRep = findRep(x);
            int yRep = findRep(y);
            
            if(xRep == yRep)
                return false;
            
            if(rank[xRep] < rank[yRep])
            {
                parent[xRep] = yRep;
            }
            else if(rank[yRep] < rank[xRep])
            {
                parent[yRep] = xRep;
            }
            else
            {
                parent[yRep] = xRep;
                rank[xRep]++;
            }
            return true;
        }
        
        
    }
    
    
    public static ArrayList<Edge> edgeList= new ArrayList<Edge>();
    public static int nNodes, nEdges;
    
    
    public static void main(String[] args) throws FileNotFoundException {
        
        readfile();
         //mst
         Collections.sort(edgeList);
         Disjoint_Set ds = new Disjoint_Set(nNodes);
         long wSum = 0;
         for(int i = 0; i < edgeList.size(); i++)
         {
             Edge min = edgeList.get(i);
             int src = min.src;
             int dest = min.dest;
             
             if(ds.union(src, dest))
             {
                 System.out.println(src + " " + dest + " " +  min.weight);
                 wSum += min.weight;
             }
             
         }
         
         System.out.println("Total Weight :: " +wSum );
        
        
    }
    
    public static void readfile() throws FileNotFoundException
    {
       Scanner sc = new Scanner(new File("C:\\Users\\darklife\\Desktop\\Dataset(1)\\dataset\\input4.txt"));
       Edge e;
       int src, dest, w;
       nNodes = sc.nextInt();
       nEdges = sc.nextInt();
       for(int i = 0 ; i < nEdges ; i++)
       {
           src = sc.nextInt();
           dest = sc.nextInt();
           w = sc.nextInt();
           e = new Edge(src, dest,w);
           edgeList.add(e);
       }
    }

}

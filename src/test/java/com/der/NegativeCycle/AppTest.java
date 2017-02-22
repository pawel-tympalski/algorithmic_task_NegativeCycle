package com.der.NegativeCycle;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;



/**
 * Unit test for simple App.
 */
public class AppTest 
    
{
	@Test
	public void firstTest(){
		//String input = "3 3 1 2 -5 2 3 2 3 1 1";
		//String input = "4 3 1 2 -1 2 3 -2 3 4 -2";
		//String input = "4 4 1 2 -1 4 1 -7 2 3 2 3 1 1";
	//String input="3 2 2 3 1 3 2 -2";	
		//String input = "3 2 1 2 5 2 3 10";
		//String input = "5 5 1 2 1 3 1 1 3 4 -1 4 5 -1 5 3 -1";
		//String input="1 1 1 1 -10";
		//String input ="4 3 2 3 -1001 -1 3 4 -1 4 2 -1";

		String input="4 1 1 2 -5";
		
		
		Scanner scanner = new Scanner(input).useDelimiter(" ");
        int n = scanner.nextInt();
        while(n < 1 || n > 1000){
        	n = scanner.nextInt();
        }
        int m = scanner.nextInt();
        while(m < 0 || m > 10000){
        	m = scanner.nextInt();
        }
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            
            x = scanner.nextInt();
            while(x < 1 || x > 1000){
            x = scanner.nextInt();
            }
            
            y = scanner.nextInt();
            while(y < 1 || y > 1000){
            y = scanner.nextInt();
            }
           
            
            
            w = scanner.nextInt();
            while(w > 1000 || w < (-1000)){
            	w = scanner.nextInt();
            }
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
		
		int output = NegativeCycle.negativeCycle(adj, cost);
		
		assertEquals(1,output);
	}
	
	@Test
	public void massiveData(){
//String input = "4 4 1 2 -5 1 4 2 2 3 2 3 1 1";
//String input = "3 2 1 2 5 2 3 10";
		//String input = "3 3 1 2 -5 2 3 2 3 1 1";
		StringBuilder sb = new StringBuilder();
		sb.append("1000 ");
		sb.append("1000 ");
		
		for(int i = 1; i <= 999; i++){
			int j = i;
			sb.append(i + " ");
			++j;
			sb.append(j + " ");
			
			sb.append(-1000 + " ");
		}
		
		sb.append("1000 ");
		sb.append("1 ");
		sb.append("-1000");
		
		String input = sb.toString();
		
		Scanner scanner = new Scanner(input).useDelimiter(" ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
		
		int output = NegativeCycle.negativeCycle(adj, cost);
		
		assertEquals(1,output);
	}
	
}

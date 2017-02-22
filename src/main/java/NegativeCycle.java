

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.TreeSet;


public class NegativeCycle {
    public static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        
    	int[] dist = new int[adj.length];
    	int infinite = 1000*(adj.length - 1);
    	//Deque<Integer> queue = new ArrayDeque<Integer>();
    	Boolean[] checked = new Boolean[adj.length];
    	
    	setDistanceToInfinite(adj, dist, infinite);
    	//setCheckedArrayToFalse(checked);
    	
    	TreeSet<Integer> set = new TreeSet<Integer>();
    	for(int i=0;i<adj.length;i++){
    		set.add(i);
    	}
    	
    	dist[0] = 0;
    	
    	ArrayList<Integer>[] costCopy = (ArrayList<Integer>[])new ArrayList[cost.length];
    	
    	for(int r=1;r<adj.length;r++){
    	goThroughAllVertices(adj, cost, dist, set, costCopy);
    	}
    	
    	
    	int[] distCopy = Arrays.copyOfRange(dist, 0, dist.length);
    	
    	boolean check =false;
    	
    	goThroughAllVertices(adj, cost, dist, set, costCopy);
    	
    	check = checkDistance(dist, distCopy, check);
    	
    	if(check == true){
    		return 1;
    	}
    	else{
    		return 0;
    	}
    }

	public static boolean checkDistance(int[] dist, int[] distCopy, Boolean check) {
		for(int w=0; w < dist.length; w++){
    		if(dist[w] != distCopy[w]){
    			check = true;
    			break;
    		}
    	}
		return check;
	}

	public static void goThroughAllVertices(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int[] dist,
			TreeSet<Integer> set, ArrayList<Integer>[] costCopy) {
		copyCostArray(cost, costCopy);
    	
    	for(Integer u : set){
    		ArrayList<Integer> list = adj[u];
    		for(int i=0; i<list.size();i++){
    			int v = list.get(i);
    			//System.out.println(v);
    			if(dist[v] > dist[u] + costCopy[u].get(0)){
    				dist[v] = dist[u] + costCopy[u].remove(0);
    			}
    			else{
    				costCopy[u].remove(0);
    			}
    			
    		}
    	}
	}

	public static void goThroughGraphUntilQueueGraterThanZero(ArrayList<Integer>[] adj, int[] dist,
			Deque<Integer> queue, Boolean[] checked, ArrayList<Integer>[] costCopy) {
		while(queue.size() > 0){
			
			int u = queue.pollFirst();
			checked[u] = true;
			
			ArrayList<Integer> list = adj[u];
			for(int j=0; j<list.size();j++){
				int value = list.get(j);
				 if(checked[value] == false){
					 queue.addLast(value);
				 }
				
				 
				if(dist[value] > dist[u] + costCopy[u].get(0)){
					dist[value] = dist[u] + costCopy[u].remove(0);
				}
				else{
					costCopy[u].remove(0);
				}
			}
		}
	}

	public static void copyCostArray(ArrayList<Integer>[] cost, ArrayList<Integer>[] costCopy) {
		for(int t=0;t<cost.length;t++){
			ArrayList<Integer> listCost = cost[t];
			
			ArrayList<Integer> copyList = new ArrayList<Integer>();
			
			for(int j=0;j< listCost.size();j++){
				
				copyList.add(listCost.get(j));
			}
			
			costCopy[t] = copyList;
		}
	}

	public static void setDistanceToInfinite(ArrayList<Integer>[] adj, int[] dist, int infinite) {
		for(int i=0;i<adj.length;i++){
    		dist[i] = infinite;
    	}
	}

	public static void setCheckedArrayToFalse(Boolean[] checked) {
		for(int i=0; i<checked.length;i++){
    		checked[i]= false;
    	}
	}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
            
            
            y = scanner.nextInt();
            
            
            
            w = scanner.nextInt();
            while(w > 1000 || w < -1000){
            	w = scanner.nextInt();
            }
            
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}


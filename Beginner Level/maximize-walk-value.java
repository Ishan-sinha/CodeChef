/*

Maximize Walk Value

There's a tree and every one of its nodes has a cost associated with it. Some of these nodes are labelled special nodes. You are supposed to answer a few queries on this tree. 
In each query, a source and destination node (SNODE and DNODE) is given along with a value W. For a walk between SNODE and DNODE to be valid you have to choose a special node 
and call it the pivot P. Now the path will be SNODE ->P -> DNODE. For any valid path, there is a path value (PV) attached to it. It is defined as follows:
Select a subset of nodes(can be empty) in the path from SNODE to P (both inclusive) such that sum of their costs (CTOT1) doesn't exceed W.
Select a subset of nodes(can be empty) in the path from P to DNODE (both inclusive) such that sum of their costs (CTOT2) doesn't exceed W.
Now define PV=CTOT1+CTOT2 such that the absolute difference x=|CTOT1−CTOT2| is as low as possible. If there are multiple pairs of subsets that give the same minimum absolute 
difference, the pair of subsets which maximize PV should be chosen. For each query, output the path value PV minimizing x as defined above.
Note that the sum of costs of an empty subset is zero.

Input
First line contains three integers N - number of vertices in the tree, NSP - number of special nodes in the tree and Q - number of queries to answer.
Second line contains N−1 integers. If the ith integer is Vi then there is an undirected edge between i+1 and Vi (i starts from 1 and goes till N−1).
Third line contains N integers, the ith integer represents cost of the ith vertex.
Fourth line contains NSP integers - these represent which nodes are the special nodes.
Following Q lines contains three integers each - SNODE, DNODE and W for each query.

Output
For each query output a single line containing a single integer - the path value PV between SNODE and DNODE.

Constraints:
1≤ Number of nodes ≤1000
0≤W≤1000
1≤ Number of special nodes ≤10
0≤ Cost of each node ≤1000
1≤ Number of queries ≤1000
Sample Input
7 1 5
1 1 2 2 3 3
3 5 4 2 7 9 1
1
2 3 100
1 1 100
2 1 100
4 5 100
4 7 100

Sample Output:
6
6
6
20
16

Explanation:
Consider query 4. The only path is 4−>2−>1−>2−>5. The two sets defined for this path are {3,2,5} and {3,5,7}. Pick subsets {3,2,5} and {3,7} from each set which minimizes PV. 
Note that node 2 can repeat as it is in different paths (both to and from the pivot). */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    static int[] S = new int[1002];
	static int[] SS = new int[11];
	static boolean[][][] p = new boolean[11][1002][1002];
	static Vector<Vector<Integer>> adj = new Vector<Vector<Integer>>();
	 
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int NSP = sc.nextInt();
		int Q = sc.nextInt();
		
		for (int i = 0; i <= N; i++) adj.add(new Vector<Integer>());		
		for (int i = 1; i < N; i++) {
			int v = sc.nextInt();
			adj.get(v).add(i + 1);
			adj.get(i + 1).add(v);
		}
		for (int i = 1; i <= N; i++) S[i] = sc.nextInt();
		for (int i = 1; i <= NSP; i++) {
			SS[i] = sc.nextInt();
			p[i][0][0] = true;
			dfs(i, SS[i], 0);
		}
		
		while (Q-- > 0) {
			int st = sc.nextInt();
			int tg = sc.nextInt();
			int W = sc.nextInt();
			
			int ans = 0;
			for (int i = 1; i <= NSP; i++) {
				for (int j = W; j > 0; j--) {
					if (p[i][st][j] && p[i][tg][j]) {
						ans = Math.max(ans, j);
						break;
					}
				}
			}
			System.out.println(2*ans);
		}
	}
	
	private static void dfs(int i, int j, int k)
	{
		for (int w = 0; w < 1001; w++) {
			if (p[i][k][w]) {
				p[i][j][w] = true;
				if (w + S[j] < 1001)  p[i][j][w + S[j]] = true;
			}
		}	
		for (int x : adj.get(j)) if (x != k) dfs(i, x, j);
	}
}

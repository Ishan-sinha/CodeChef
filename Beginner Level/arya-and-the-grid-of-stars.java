/*
Arya and the grid of stars

Arya is planning to go back to Winterfell. In order to reach there and survive, she must collect maximum stars from a maze. The maze is in the form of a grid of size 
[Math Processing Error]. Each cell is either blank, has a star or has a stone.

She starts from [Math Processing Error] to reach [Math Processing Error] using right and bottom moves and then she returns to [Math Processing Error] using top and left moves.

The only constraint is that if she visits some cells in a particular row in the whole journey (forward and backward), then maximum distance between any pair of visited cells 
(forward and backward) for every row is [Math Processing Error], where [Math Processing Error] is given in the input. Distance between [Math Processing Error] and [Math Proce
-ssing Error] is [Math Processing Error]. Also note that she cannot go into the cells with a stone. Tell the maximum stars that can be collected by Arya.

Input:
First line will contain three integers: [Math Processing Error], number of rows, [Math Processing Error], number of columns and [Math Processing Error], the distance constraint.
Next [Math Processing Error] lines contain the grid. Each line contains '.', '*' or '#' denoting empty cell, star or stone. The cells [Math Processing Error] or 
[Math Processing Error] can have stones.

Output:
Print the maximum number of stars that can be collected by Arya. If it is not possible to reach [Math Processing Error] from [Math Processing Error], then print -1.

Sample Input:
3 3 0
.*.
*.*
.*.
Sample Output:
-1
EXPLANATION:
If x = 0, then it is not possible to reach 3,3 as we cannot move left in any row..

Sample Input:
3 3 1
.*.
*.*
.*.
Sample Output:
3
EXPLANATION:
If x = 1, then we can at most visit 2 consecutive cells in any row. Thus (0,0) => (0,1) => (1,1) => (1,2) => (2,2) => (2,1) => (1,1) => (0,1) => 
(0,0) OR (0,0) => (1,0) => (1,1) => (2,1) => (2,2) => (2,1) => (1,1) => (0,1) => (0,0) Note that in this case, we cannot go to (1, 2) 
while returning as it will lead to maximum distance of 3 between (1,0) and (1,2).

Sample Input:
3 3 2
.*.
*.*
.*.
Sample Output:
4
EXPLANATION:
(0,0) => (0,1) => (0,2) => (1,2) => (2,2) => (2,1) => (2,0) => (1,0) => (0,0)  */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static boolean is_test = false;
	
	static int m, n, x;
	static String[] grid;
	static int[][][] dp; 
		
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		x = sc.nextInt();
		
		grid = new String[m];
		for (int i = 0; i < m; i ++) {
			grid[i]  = sc.next();
		}
		sc.close();

		dp =  new int[m][n][x + 1];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k <= x; k++)
					dp[i][j][k] = -1;
							
		int ans = 0;
		ans = Math.max(-1, solve(0, 0, 0));
		System.out.println(ans);
		
	}
	
	private static boolean isAvailable(int X, int Y)
	{
		if (Y < 0 || Y >= m) return false;
		if (X < 0 || X >= n || grid[Y].charAt(X) == '#') return false;
		else return true;
	}
	
	private static int solve(int Y, int XDown, int XUp)
	{
		if (Y == m) {
			if (XDown == n - 1) return 0;
			else return Integer.MIN_VALUE;
		}
		
		if (dp[Y][XDown][XUp - XDown] != -1) return dp[Y][XDown][XUp - XDown];
		
		int result = Integer.MIN_VALUE;
		int starsUp = 0;
		for (int XNextUp = XUp; XNextUp <= XDown + x; XNextUp++ ) {
			if (!isAvailable(XNextUp, Y)) break;
			if (grid[Y].charAt(XNextUp) == '*') starsUp++;
			int starsDown = 0;
			for (int XNextDown = XDown; XNextDown <= XNextUp; XNextDown++) {
				if (!isAvailable(XNextDown, Y)) break;
				if (grid[Y].charAt(XNextDown) == '*') if (XNextDown < XUp) starsDown++;
				int subResult = solve(Y + 1, XNextDown, XNextUp);
				result = Math.max(result, subResult + starsDown + starsUp);
			}		
		}
		
		dp[Y][XDown][XUp - XDown] = result;
		return result;
	}
	

	
	public static void pnl(Object o)
	{
		if (is_test) System.out.println(o);
	}

	public static void pn(Object o)
	{
		if (is_test) System.out.print(o + " ");
	}
}


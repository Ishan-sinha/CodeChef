/*
Surgical Strikes

N  Indian Air Force fighter planes are located in different bases across the country. Each airbase is described by some integer coordinate (x,y). 
The Air Force plans to do surgical strikes on a maximum of M different targets in enemy territory (which are also described by cartesian coordinates) 
and then come back to the common main airbase at coordinate (baseX,baseY) .

Each army base and the targets are recognised by a secret integer ID. The time taken for an aircraft to go from a base to a target is the prime factor 
of the Manhattan Distance between the base and the target that is just greater than the ID of the source base (In case the ID is greater than or equal 
to the largest prime factor, then consider the ID itself). Similarly, the time taken for an aircraft to go from a target to the main base is the prime 
factor of the Manhattan Distance between the target and the main base that is just greater than the ID of the target (In case the ID is greater than or 
equal to the largest prime factor, then consider the ID itself).

Each Aircraft needs to leave the base, reach target and come back to the main base in a maximum time of T. One aircraft can go to only one target before going to the main base.

Find the maximum number of targets that can be reached in the enemy territory.

Input
The first line contains three space separated integers N, M and T respectively.
The next N lines contain 3 integers denoting x coordinate, y coordinate and the ID of the air bases.
The next M lines contain 3 integers denoting x coordinate, y coordinate and the ID of the targets.
The last line contains two integers denoting the baseX and baseY coordinate.
Output
Output a single integer which is the maximum number of targets that can be reached.

Constraints
1≤N,M≤400
0≤x,y,baseX,baseY≤5∗106
0≤ID≤5000
0≤T≤107
Sample Input
2 2 35
1 2 15
2 10 20
2 1 8
5 6 12
5 5

Sample Output
2

Sample Explanation
Aircraft from first base can go to target 1 and then to the main base in time 23 and aircraft from second base can go to target 2 and then to the main 
base in time 32. So 2 targets can be reached in less than time T=35. */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SurgicalStrike {

	static class Reader {
		static BufferedReader reader;
		static StringTokenizer tokenizer;

		static void init(InputStream input) {
			reader = new BufferedReader(new InputStreamReader(input));
			tokenizer = new StringTokenizer("");
		}

		static String next() throws IOException {
			while (!tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			String ans=tokenizer.nextToken();
			return ans;
		}

		static int nextInt() throws IOException {
			int ans=Integer.parseInt(next());
			return ans;
		}

		static double nextDouble() throws IOException {
			double ans=Double.parseDouble(next());
			return ans;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Reader.init(System.in);
		int N = Reader.nextInt();
		int M = Reader.nextInt();
		int T = Reader.nextInt();
		Pair[] s = new Pair[N];
		for(int i=0;i<N;i++){
		    s[i] = new Pair(Reader.nextInt(), Reader.nextInt(), Reader.nextInt());
		}
		Pair[] t = new Pair[M];
		for(int i=0;i<M;i++){
		    t[i] = new Pair(Reader.nextInt(), Reader.nextInt(), Reader.nextInt());
		}
		Pair main = new Pair(Reader.nextInt(), Reader.nextInt());
		int[][] arr = new int[N][M];
		int[] col = new int[M];
		int[] row = new int[N];
		for(int i=0;i<N;i++) {
			for(int j=0; j<M;j++) {
				int mdist=Math.abs(s[i].x-t[j].x)+ Math.abs(s[i].y - t[j].y);
				mdist=primeFactors(mdist, s[i].id);
				int mdist2=Math.abs(main.x-t[j].x)+ Math.abs(main.y - t[j].y);
				mdist2=primeFactors(mdist2, t[j].id);
				int dist=T-mdist-mdist2;
				if(dist>=0) {
					arr[i][j]=1;
					col[j]++;
					row[i]++;
//					System.out.println(i+" "+row[i]+" "+j+" "+col[j]);
				}
			}
		}
		int count=0;
		for(int i=0;i<N;i++) {
			int least=Integer.MAX_VALUE;
			int x=-1;
			int y=-1;
			for(int j=0; j<M;j++) {
				if(i!=0 && arr[i-1][j]==-1) {
		            arr[i][j] = -1;
		            continue;
		        }
				if(arr[i][j]==1) {
					int sum=row[i]+col[j]-1;
					if(least>sum) {
//						System.out.println(sum);
						least=sum;
						x=i;
						y=j;
					}
				}
			}
			if(x!=-1 && y!=-1) {
				count++;
				arr[x][y]=-1;
			}
		}
		System.out.println(count);
		
	}
	
	public static int primeFactors(int n, int id) { 
	    while (n%2==0) 
        { 
            if(2>id) return 2; 
            n /= 2; 
        }
        
        for (int i = 3; i <= Math.sqrt(n); i+=2) { 
            while (n%i == 0) { 
                if(i>id) return i;
                n /= i; 
            } 
        } 
  
        if (n > 2 && n > id) 
            return n; 
        else 
            return id;
    } 
	
	public static class Pair{
		int x;
		int y;
		int id;
		Pair(int x, int y, int id) {
			this.x=x;
			this.y=y;
			this.id=id;
		}
		Pair(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	

}

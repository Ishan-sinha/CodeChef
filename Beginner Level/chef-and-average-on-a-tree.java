/*
Chef and Average on a Tree

Chef has a tree with N nodes. Each node of the tree has an integer weight associated with it.

Let's define the cost of a sequence of numbers as the arithmetic mean of all elements of the sequence.

Next, let's define the cost of a path in the tree as the cost of the sequence of weights of all nodes belonging to the path. (It's possible for a path to contain only one node.)

A set of paths in the tree is called a correct path decomposition if each node of the tree belongs to exactly one of the paths from this set. The cost of a correct decomposition is defined as the minimum of costs of all paths in this decomposition.

Chef would like to find the maximum cost of a correct decomposition. Can you help him?

Input
The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
The first line of each test case contains a single integer N.
The second line contains N space-separated integers A1, A2, ..., AN denoting the weights of nodes of the tree.
Each of the following N-1 lines contains two space-separated integers x and y denoting an edge between nodes x and y.
Output
For each test case, print the maximum possible cost among all correct decompositions. You answer will be considered correct if its absolute error does not exceed 10-6.

Constraints
1 ≤ T ≤ 20
1 ≤ N ≤ 100,000
1 ≤ sum of N over all test cases ≤ 200,000
1 ≤ Ai ≤ 100,000
Subtasks
Subtask #1 (15 points, time limit 1 second):

1 ≤ N ≤ 200
1 ≤ sum of N over all testcases ≤ 400
for each i (1 < i ≤ N), there is an edge connecting nodes i and i-1
Subtask #2 (35 points, time limit 1 second):

1 ≤ N ≤ 200
1 ≤ sum of N over all test cases ≤ 400
Subtask #3 (50 points, time limit 3 seconds): original constraints

Example
Input:
	
2
2
2 3
1 2
5
4 3 5 2 1
5 3
1 3
2 1
3 4

Output:

2.5000000
2.6666667
Explanation
https://codechef_shared.s3.amazonaws.com/download/upload/LTIME56/AVG-sample.png
Example case 1: It's better to use one path containing both nodes (blue in the picture above) rather than keep each node in a separate one-node path, since the first way gives decomposition cost equal to (A1 + A2) / 2 = (2+3) / 2 = 2.5, while the second one gives decomposition cost min(A1 / 1, A2 / 1) = min(2 / 1, 3 / 1) = 2.

Example case 2: It's optimal to decompose the tree into three paths (coloured red, green and yellow in the picture). This way, the red path has cost (A3 + A4 + A5) / 3 = (5 + 2 + 1) / 3 = 8 / 3 = 2.6666667, the green path has cost A1 = 4 and the yellow path has cost A2 = 3. Thus, the cost of this decomposition is 2.6666667.

It's possible to merge the green and yellow paths into one path with cost 3.5 without changing the decomposition cost. Any other decomposition has smaller cost. */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.PrintWriter;
import java.util.ArrayList;
class L56AVG 
{
    static class Scanner
    {
        BufferedReader br;
        StringTokenizer tk=new StringTokenizer("");
        public Scanner(InputStream is) 
        {
            br=new BufferedReader(new InputStreamReader(is));
        }
        public int nextInt() throws IOException
        {
            if(tk.hasMoreTokens())
                return Integer.parseInt(tk.nextToken());
            tk=new StringTokenizer(br.readLine());
            return nextInt();
        }
        public long nextLong() throws IOException
        {
            if(tk.hasMoreTokens())
                return Long.parseLong(tk.nextToken());
            tk=new StringTokenizer(br.readLine());
            return nextLong();
        }
        public String next() throws IOException
        {
            if(tk.hasMoreTokens())
                return (tk.nextToken());
            tk=new StringTokenizer(br.readLine());
            return next();
        }
        public String nextLine() throws IOException
        {
            tk=new StringTokenizer("");
            return br.readLine();
        }
        public double nextDouble() throws IOException
        {
            if(tk.hasMoreTokens())
                return Double.parseDouble(tk.nextToken());
            tk=new StringTokenizer(br.readLine());
            return nextDouble();
        }
        public char nextChar() throws IOException
        {
            if(tk.hasMoreTokens())
                return (tk.nextToken().charAt(0));
            tk=new StringTokenizer(br.readLine());
            return nextChar();
        }
        public int[] nextIntArray(int n) throws IOException
        {
            int a[]=new int[n];
            for(int i=0;i<n;i++)
                a[i]=nextInt();
            return a;
        }
        public long[] nextLongArray(int n) throws IOException
        {
            long a[]=new long[n];
            for(int i=0;i<n;i++)
                a[i]=nextLong();
            return a;
        }
        public int[] nextIntArrayOneBased(int n) throws IOException
        {
            int a[]=new int[n+1];
            for(int i=1;i<=n;i++)
                a[i]=nextInt();
            return a;
        }
        public long[] nextLongArrayOneBased(int n) throws IOException
        {
            long a[]=new long[n+1];
            for(int i=1;i<=n;i++)
                a[i]=nextLong();
            return a;
        }
    
    
    }
    
    public static void main(String args[]) throws IOException
    {
        new Thread(null, new Runnable() {
            public void run() {
                try
                {
                    solve();
                }
                catch(Exception e)
                {
                    System.out.println(1/0);
                }
            }
        }, "1", 1 << 26).start();
        
    }
    static ArrayList<Integer> g[];
    static long wt[];
    static long w[];
    static long dfs(int node,int parent)
    {
        
        ArrayList<Long> neg=new ArrayList<>();
        ArrayList<Long> pos=new ArrayList<>();
        for(int i=0;i<g[node].size();i++)
        {
            int ne=g[node].get(i);
            if(ne==parent)
                continue;
            long val=dfs(ne,node);
            if(val==Long.MIN_VALUE)
                return val;
            if(val<0)
                neg.add(val);
            else if(val>0)
                pos.add(val);
            
        }
        if(neg.size()>2)
            return Long.MIN_VALUE;
        if(neg.size()==2)
        {
            long vv=neg.get(0)+neg.get(1)+w[node];
            if(vv>=0)
                return 0;
            return Long.MIN_VALUE;
        }
        long max1=0;
        long max2=0;
        for(int i=0;i<pos.size();i++)
        {
            if(pos.get(i)>=max1)
            {
                max2=max1;
                max1=pos.get(i);
            }
            else
                max2=Math.max(max2,pos.get(i));
        }
        if(neg.size()==1)
        {
            if(w[node]+neg.get(0)>=0)
                return w[node]+neg.get(0);
            if(w[node]+neg.get(0)+max1>=0)
                return 0;
            else
                return w[node]+neg.get(0);
        }
        if(w[node]+max1>=0)
            return w[node]+max1;
        if(w[node]+max1+max2>=0)
            return 0;
        else
            return w[node]+max1;
    }
    static long bs(int n)
    {
        long start=0;
        long end=50000000000010l;
        long ans=0;
        while(start<=end)
        {
            long mid=(start+end)/2;
            for(int i=1;i<=n;i++)
                w[i]=wt[i]-mid;
            if(dfs(1,0)>=0)
            {
                start=mid+1;
                ans=Math.max(ans,mid);
            }
            else
                end=mid-1;
        }
        return ans;
    }
    static void solve() throws IOException
    {
        Scanner in=new Scanner(System.in);
        PrintWriter out=new PrintWriter(System.out);
        int t=in.nextInt();
        while(t--!=0)
        {
            int n=in.nextInt();
            g=new ArrayList[n+1];
            for(int i=1;i<=n;i++)
                g[i]=new ArrayList<>();
            wt=new long[n+1];
            w=new long[n+1];
            for(int i=1;i<=n;i++)
                wt[i]=in.nextLong()*500000000;
            
            for(int i=1;i<n;i++)
            {
                int x=in.nextInt();
                int y=in.nextInt();
                g[x].add(y);
                g[y].add(x);
            }
            long ans=bs(n);
            double d=ans;
            d/=500000000.0;
            out.format("%.8f",d);
            out.println();
        }
        
        out.close();
    }

}

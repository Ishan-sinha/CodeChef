/*
Expected Candies

There are two friends Alice and Bob. They have a packet of n candies. They want to split the candies between them, and to do so, they kept them all in a single line. 
The ith candy in the line has a sweetness value of ai. They decided to take turns in picking the candies. Alice has the first turn. In a person's turn, they either eat 
the leftmost candy or the rightmost candy with equal probability.

Can you find the expected value of sum of sweetness of the candies eaten by Alice?

Your answer is considered correct if its absolute or relative error doesn't exceed 10−6 .

Input:
The first line will contain T, number of testcases. Then the testcases follow.
First line of each testcase contains a single integer n denoting the number of candies.
Second line of each testcase contains n space separated integers representing a1,a2,a3,...an respectively.
Output:
For each testcase, output the expected value of sum of sweetness of the candies which Alice eats.

Constraints
1≤T≤10
1≤n≤103
1≤ai≤106
Sum of n across all the tests ≤103
Sample Input:
2
2
1 2
3
1 2 3
Sample Output:
1.500000000000000
4.000000000000000
EXPLANATION:
In the first sample, Alice eats either the first or the second candy with equal probability. Hence, expected value is (1+2)/2=1.5
For the second sample, expected value is 4.   */

import java.util.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.io.*;
import java.math.*;
 
/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	double dp1[][], dp2[][];
	public static void main (String[] args) throws IOException
	{
		Reader s=new Reader();
		PrintWriter pt=new PrintWriter(System.out);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=s.nextInt();
		while(T-->0)
		{
		   int n=s.nextInt();
		   double arr[]=new double[n];
		   for(int i=0;i<n;i++)
			   arr[i]=s.nextDouble();
		   Codechef obj=new Codechef();
		   obj.dp1=new double[n][n];
		   obj.dp2=new double[n][n];
		   for(int i=0;i<n;i++)
		   {
			   Arrays.fill(obj.dp1[i], 0);
			   Arrays.fill(obj.dp2[i], 0);
		   }
		   pt.println(obj.expectation(0, n-1, arr, true));
		}
		pt.close();
	}
	double expectation(int i, int j, double arr[], boolean alice)
	{
		if(alice)
		{
			if(dp1[i][j]>0)
				return dp1[i][j];
			else if(i==j)
				return dp1[i][j]=arr[i];
			else
				return dp1[i][j]=(arr[i]+expectation(i+1,j,arr,false)+arr[j]+expectation(i,j-1,arr, false))/2.0;
		}
		else
		{
			if(dp2[i][j]>0)
				return dp2[i][j];
			else if(i==j)
				return dp2[i][j]=0;
			else
				return dp2[i][j]=(expectation(i+1,j,arr,true)+expectation(i,j-1,arr,true))/2.0;
		}
	}
	void merge(int arr[], int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    // Main function that sorts arr[l..r] using 
    // merge() 
    void sort(int arr[], int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            sort(arr, l, m); 
            sort(arr , m+1, r); 
  
            // Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    } 
	static class Reader 
    { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public Reader(String file_name) throws IOException 
        { 
            din = new DataInputStream(new FileInputStream(file_name)); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        public double nextDouble() throws IOException 
        { 
            double ret = 0, div = 1; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
  
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
  
            if (c == '.') 
            { 
                while ((c = read()) >= '0' && c <= '9') 
                { 
                    ret += (c - '0') / (div *= 10); 
                } 
            } 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
  
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 
  
        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 
  
        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    } 
}

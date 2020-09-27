/*
After the death of their mother, Alphonse and Edward now live with Pinako and Winry.
Pinako is worried about their obsession with Alchemy, and that they don't give attention to their studies.

So to improve their mathematical solving ability, every day she gives a mathematical problem to solve. They can go out to practice Alchemy only after they have solved the problem. Help them by solving the given problem, so that they can go early today for their Alchemy practice.

Given an array A of N non-negative integers and two integers K and M. Find the number of subsequences of array A of length K which satisfies the following property:
Suppose the subsequence is S=S1S2…SK, then for all i such that 1≤i≤K,
Si%M=i%M
should hold true, where Si denotes the i-th element of the subsequence, using 1-based indexing.

As the number of subsequences may be very large, output the answer modulo 1000000007.

PS: We also proposed the idea of making a look-alike clone through alchemy and keeping it in front of the study table. But it seems impossible to convince Edward to make a clone of his exact same height, and not taller than him. So solving the problem for him was a better choice.

Input:
The first line contains T, the number of test cases. Then the test cases follow.
For every test case, the first line contains N, K and M.
For every test case, the second line contains N integers Ai ( 1≤i≤N).
Output:
For every test case, output in a single line an integer denoting the number of valid subsequences modulo 109+7
Constraints
1≤T≤100
1≤N≤104
1≤K≤N
⌈K100⌉≤M≤100×K
0≤Ai≤109
Sample Input:
1
12 4 3
4 5 6 7 1 4 6 9 0 0 10 2
Sample Output:
8
Explanation:
The subsequences of length 4, satisfying the given criteria are [4,5,6,7], [4,5,6,10], [4,5,6,10], [4,5,6,1], [4,5,9,10] ,[4,5,6,4] , [4,5,0,10] and [4,5,0,10]. This accounts for a total of 8 valid subsequences.

Let us take one subsequence and see why it satisfies the given property. Consider [4,5,9,10].

S1%M=4%3=1=1%3=1%M
S2%M=5%3=2=2%3=2%M
S3%M=9%3=0=3%3=3%M
S4%M=10%3=1=4%3=4%M
All the valid i satisfy the condition, and hence this is a valid subsequence. */

/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class Main
{    
	static int mod=1000000007;
    static void solved(int arr[],int n,int k,int m){
       int ans[]=new int[k+1];
       for(int i=n-1;i>=0;i--){
    	    while(arr[i]<=k){
    	    	if(arr[i]==0){
    	    		arr[i]+=m;
    	    		continue;
    	    	}
    	    	else if(arr[i]==k){
    	    		ans[arr[i]]+=1;
    	    	}
    	    	else{
    	    		ans[arr[i]]=(ans[arr[i]]+ans[arr[i]+1])%mod;
    	    	}
    	    	arr[i]+=m;
    	    }
       }
       System.out.println(ans[1]%mod);
    }
	public static void main(String[] args) throws IOException {
		String str;
		String []string;
		int n,k,m;
		BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
 		str=buf.readLine();
 		int t=Integer.parseInt(str);
        while(t>0){
        	string=buf.readLine().split(" ");
        	n=Integer.parseInt(string[0]);
        	k=Integer.parseInt(string[1]);
        	m=Integer.parseInt(string[2]);
        	String strarr[]=buf.readLine().split(" ");
        	int arr[]=new int[n];
        	for(int i=0;i<n;i++){
        		arr[i]=Integer.parseInt(strarr[i]);
        		arr[i]=arr[i]%m;
        	}
            solved(arr,n,k,m);
            --t;
       
        }
	}
}

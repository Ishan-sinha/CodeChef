/*
Chef and Words

Nobody knows but Chef can play with words! He has a word. He claps and each letter of the word transform to another letter(independently)! 
All letters are only in range a-z. For each pair of symbols from 'a' to 'z' there is a probability that first symbol will transform into 
second one after clapping. Note that the symbol can also transform into the same symbol.

Also Chef has a list of words. He asks you what is the probability that after exactly K claps Chef's word will equal one of the words from list?

 

Input
>First line contains integer T denoting the number of test cases.
>The first line of each test case contains two integers N and K denoting the number of words in list and number of clappings.
>The second line contains string S denoting the Chef's word.
>Each of the next 26 lines contains 26 space separated doubles. The jth double in the ith line denotes the probability that the 
 ith small letter of English alphabet will transform   to the jth one afterclapping. Letter a has number 1.
>Each of the next N lines contains string si - next word from the list.

Output
For each test case in a single line print the probability that after exactly K clappings the Chef's word will be equal to one from the list.
Answer will be considered as correct if absolute difference between the answer and correct answer is less or equal 10^(-6)
 

Constraints
1 ≤ T ≤ 50
The total length of all words in one test file is less or equal 10^6.
1 ≤ |S|, |si| ≤ 3
1 ≤ N ≤ 10^5
1 ≤ K ≤ 10^9
0 ≤ aij ≤ 1
Sum of ai, 1 + ai, 2 + ... + ai, 26 = 1
All words contains only letters from a to z.

Subtasks
Subtask 1: T ≤ 50; N ≤ 100, K ≤ 3. Points: 15
Subtask 2: T ≤ 50; The sum of N within one test file ≤ 10^5; K ≤ 10^5. Points: 25
Subtask 3: T ≤ 50; N ≤ 10^5; K ≤ 10^9. Points: 60
 

Example
Input:
1
4 1
abc
0.25 0.25 0.25 0.25 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0.50 0.50 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
0.1 0.9 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
daa
bbb
ccc
zz
Output:
0.125000000000000
 

Explanation
daa (a->d) * (b->a) * (c->a) | 0.25*0.5*0.1 = 0,0125
bbb (a->b) * (b->b) * (c->b) | 0.25*0.5*0.9 = 0,1125
ccc (a->c) * (b->c) * (c->c) | 0
zz (a->z) * (b->z) * (c can't transform to empty space as it is not allowed). */

	import java.io.*;
	import java.util.*;

	class Solution{

		public static double[][] multiply(double a[][])
		{
			double ans[][]=new double[26][26];
				for(int i=0;i<26;i++)
				{
					for(int j=0;j<26;j++)
					{
						for(int k=0;k<26;k++)
							ans[i][j]+=a[i][k]*a[k][j];
					}
				}
				return ans;
		}

		public static double[][] findpower(double x[][],int k)
		{
			if(k==0)
			{
				double ans[][]=new double[26][26];

				for(int i=0;i<26;i++)
				{
					for(int j=0;j<26;j++)
						if(i==j)
							ans[i][j]=1;
				}

				return ans;
			}
			else if(k%2==0)
			{
				double a[][]=findpower(x,k/2);
				return multiply(a);
			}
			else
			{
				double a[][]=findpower(x,(k-1)/2);
				double mid[][]=multiply(a);
				double ans[][]=new double[26][26];

				for(int i=0;i<26;i++)
				{
					for(int j=0;j<26;j++)
					{
						for(int l=0;l<26;l++)
							ans[i][j]+=x[i][l]*mid[l][j];
					}
				}
				return ans;

			}
		}

		
		public static void main(String args[]) throws IOException
		{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out=new PrintWriter(System.out);

			int t=Integer.parseInt(br.readLine());


			while(t-->0)
			{
				String s[]=br.readLine().split(" ");
				int n=Integer.parseInt(s[0]);
				int k=Integer.parseInt(s[1]);

				String word=br.readLine();

				double x[][]=new double[26][26];

				for(int i=0;i<26;i++)
				{
					s=br.readLine().split(" ");
					for(int j=0;j<26;j++)
						x[i][j]=Double.parseDouble(s[j]);
				}

				Set<String> set=new HashSet<String>();
				String find[]=new String[n];
				for(int i=0;i<n;i++)
					find[i]=br.readLine();

				double xk[][]=findpower(x,k);

			
				double ans=0;
				for(int i=0;i<n;i++)
				{
					double inter=1;
					if(find[i].length()==word.length() && !set.contains(find[i]))
					{
						set.add(find[i]);
						int len=word.length();
						for(int j=0;j<len;j++)
						{
							inter*=xk[word.charAt(j)-'a'][find[i].charAt(j)-'a'];
						}

						ans+=inter;	
					}

					
				}

				if(ans<1)
					out.println(ans);
				else
					out.println(1.000000);



			}
		
			out.close();

		}
	}		


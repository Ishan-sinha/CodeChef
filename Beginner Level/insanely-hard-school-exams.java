/*

Insanely Hard School Exams

After failing to clear his school mathematics examination, infinitepro decided to prepare very hard for his upcoming re-exam, starting with the topic he is weakest at ― computational geometry.

Being an artist, infinitepro has C pencils (numbered 1 through C); each of them draws with one of C distinct colours. He draws N lines (numbered 1 through N) in a 2D Cartesian coordinate system; for each valid i, the i-th line is drawn with the ci-th pencil and it is described by the equation y=ai⋅x+bi.

Now, infinitepro calls a triangle truly-geometric if each of its sides is part of some line he drew and all three sides have the same colour. He wants to count these triangles, but there are too many of them! After a lot of consideration, he decided to erase a subset of the N lines he drew. He wants to do it with his eraser, which has length K.

Whenever erasing a line with a colour i, the length of the eraser decreases by Vi. In other words, when the eraser has length k and we use it to erase a line with a colour i, the length of the eraser decreases to k−Vi; if k<Vi, it is impossible to erase such a line.

Since infinitepro has to study for the re-exam, he wants to minimise the number of truly-geometric triangles. Can you help him find the minimum possible number of truly-geometric triangles which can be obtained by erasing a subset of the N lines in an optimal way? He promised a grand treat for you if he passes the examination!

Input
The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
The first line of the input contains three space-separated integers N, C and K.
N lines follow. For each i (1≤i≤N), the i-th of these lines contains three space-separated integers ai, bi and ci.
The last line contains C space-separated integers V1,V2,…,VC.
Output
For each test case, print a single line containing one integer ― the smallest possible number of truly-geometric triangles after erasing lines.

Constraints
1≤T≤10
1≤C≤N≤3,000
0≤K≤3,000
0≤ai,bi≤109 for each valid i
1≤ci≤C for each valid i
0≤Vi≤K for each valid i
no two lines coincide, regardless of their colours
no three lines are concurrent
Subtasks
Subtask #1 (10 points):

N≤10
K≤100
Subtask 2 (15 points):

V1=V2=…=VC
no two lines are parallel
Subtask #3 (25 points): no two lines are parallel

Subtask #4 (50 points): original constraints

Example Input
2
7 2 13
1 10 1
1 14 2
6 4 1
2 2 1
0 12 2
2 11 2
0 6 1
8 10
6 1 20
1 5 1
2 11 1
4 0 1
6 8 1
0 11 1
3 3 1
9
Example Output
2
4
Explanation
Example case 1: We can remove exactly one line. Initially, we have 5 truly geometric triangles (see the image below; red is colour 1 and green is colour 2).

Removing any line with colour 2 brings the total number of truly-geometric triangles down to 4+0=4.
Removing any line with colour 1 brings the total number of truly-geometric triangles down to 1+1=2.
Thus, the smallest number of truly-geometric triangles we can obtain is 2. */


import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        InsanelyHardSchoolExams solver = new InsanelyHardSchoolExams();
        solver.solve(1, in, out);
        out.close();
    }

    static class InsanelyHardSchoolExams {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int t = in.nextInt();
            while (t-- > 0) {
                int n = in.nextInt(), c = in.nextInt(), k = in.nextInt(), x, y, z;
                HashMap<Integer, Integer>[] hm = new HashMap[c + 1];
                for (int i = 1; i <= c; i++)
                    hm[i] = new HashMap<>();
                for (int i = 0; i < n; i++) {
                    x = in.nextInt();
                    y = in.nextInt();
                    z = in.nextInt();
                    hm[z].put(x, hm[z].getOrDefault(x, 0) + 1);
                }
                x = 0;
                long[] b = new long[n];
                long[] v = new long[n];
                int[] w = new int[n];
                long sum = 0;
                for (int i = 1; i <= c; i++) {
                    z = in.nextInt();
                    int[] a = new int[hm[i].size()];
                    y = 0;
                    for (int e : hm[i].values())
                        a[y++] = e;
                    in.shuffle(a);
                    Arrays.sort(a);
                    y = 0;
                    for (int j = 0; j < a.length; j++) {
                        while (a[j] > 0) {
                            long ct1 = 0, ct2 = 0, ct3 = 0;
                            for (int e : a) {
                                ct1 += e;
                                ct2 += (e * e);
                                ct3 += (e * e * e);
                            }
                            b[x] = (ct1 * ct1 * ct1 + 2 * ct3 - 3 * ct1 * ct2) / 6;
                            w[x] = z;
                            if (y == 0)
                                sum += b[x];
                            x++;
                            y++;
                            a[j]--;
                        }
                    }
                }
                for (int i = 0; i < n - 1; i++)
                    v[i] = b[i] - b[i + 1];
                long[][] dp = new long[n + 1][k + 1];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j <= k; j++) {
                        if (j < w[i])
                            dp[i + 1][j] = dp[i][j];
                        else
                            dp[i + 1][j] = Math.max(dp[i][j], v[i] + dp[i][j - w[i]]);
                    }
                }
                out.println(sum - dp[n][k]);
            }
        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0) {
                    writer.print(' ');
                }
                writer.print(objects[i]);
            }
        }

        public void println(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1 << 16];
        private int curChar;
        private int snumChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int snext() {
            if (snumChars == -1)
                throw new InputMismatchException();
            if (curChar >= snumChars) {
                curChar = 0;
                try {
                    snumChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (snumChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = snext();
            while (isSpaceChar(c))
                c = snext();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = snext();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = snext();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public void shuffle(int input[]) {
            Random rand = new Random();
            for (int i = 0; i < input.length; i++) {
                int index = rand.nextInt(input.length);
                int temp = input[index];
                input[index] = input[i];
                input[i] = temp;
            }
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            boolean isSpaceChar(int ch);

        }

    }
}


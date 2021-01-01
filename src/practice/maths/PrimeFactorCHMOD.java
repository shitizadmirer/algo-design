package practice.maths;

import java.io.*;
import java.util.Comparator;
import java.util.InputMismatchException;

public class PrimeFactorCHMOD {

    Comparator<Integer> comparator = (o1, o2) -> {
        if(o1>o2){
            return 1;
        }else if (o1.equals(o2)){
            return 0;
        }else{
            return -1;
        }
    };

    public static void main(String[] args) {

        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        int n = in.readInt();

        int arr[] =new int [n];
        for(int i =0;i<n;i++){
            arr[i] = in.readInt();

        }

        int t = in.readInt();

        int [] l =new int[t];
        int [] r = new int[t];
        long [] m = new long[t];
        for(int i =0;i<t;i++){
            l[i] = in.readInt()-1;
            r[i] = in.readInt()-1;
            m[i] = in.readInt();
        }


        int [] primeFactors = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
        int [][] primesPower = new int[n][25];
        for(int i =0;i<n;i++){
            for(int j =0;j<25;j++){
                int x = arr[i];
                primesPower[i][j] = i!=0 ? primesPower[i-1][j]:0;
                while(x%primeFactors[j] ==0){
                    primesPower[i][j]++;
                    x=x/primeFactors[j];
                }
            }

//            for(int j =0;j<25;j++){
//                out.print(primesPower[i][j]);
//                out.print(" ");
//            }
//            out.printLine();
        }

        for(int i =0;i<t;i++){

            long answer = 1;

            for(int j =0;j<25;j++){
                int primeCount ;
                if(l[i] ==0 ){
                    primeCount = primesPower[r[i]][j];
                }else{
                    primeCount = primesPower[r[i]][j] - primesPower[l[i]-1][j];
                }
                long v=1;
                while(primeCount-- > 0){
                    v=(v*primeFactors[j])%m[i];
                }
                answer = (answer*v)%m[i];
            }

            out.printLine(answer);
        }
        out.close();

    }


}

//FAST IO
class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String readString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public double readDouble() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        double res = 0;
        while (!isSpaceChar(c) && c != '.') {
            if (c == 'e' || c == 'E')
                return res * Math.pow(10, readInt());
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        }
        if (c == '.') {
            c = read();
            double m = 1;
            while (!isSpaceChar(c)) {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                m /= 10;
                res += (c - '0') * m;
                c = read();
            }
        }
        return res * sgn;
    }

    public long readLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public String next() {
        return readString();
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object... objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

    public void flush() {
        writer.flush();
    }

}

/*
>>USAGE

//initialize
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

//read int
   int i = in.readInt();
//read string
   String s = in.readString();
//read int array of size N
   int[] x = IOUtils.readIntArray(in,N);
//printline
   out.printLine("X");


//flush output
   out.flush();

//remember to close the
//outputstream, at the end
   out.close();
*/

 /*
class IOUtils
	{
		public static int[] readIntArray(InputReader in, int size)
		{
            int[] array = new int[size];
            for (int i = 0; i < size; i++)
                array[i] = in.readInt();
            return array;
		}
	}
*/


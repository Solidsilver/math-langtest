import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {
    private Complex[][] mtx;
    private int m, n;

    public Matrix(Complex[][] mtx, int m, int n) {
        this.mtx = mtx;
        this.m = m;
        this.n = n;
    }

    public static Matrix readMatrix() {
        Complex[][] mtx;
        Scanner kb = new Scanner(System.in);
        int n;
        ArrayList<Complex[]> tempList = new ArrayList<>();
        String read = "";
        System.out.println("Enter the matrix below:");
        do {
            read = kb.nextLine();
            if (!read.equals("")) {
                tempList.add(readNums(read));
            }
        } while (!read.equals(""));
        mtx = new Complex[tempList.size()][];
        int i = 0;
        for (Complex[] comp: tempList) {
            mtx[i] = comp;
            i++;
        }
        return new Matrix(mtx, mtx.length, mtx[0].length);
    }

    private static Complex[] readNums(String in) {
        Scanner line = new Scanner(in);
        ArrayList<Complex> nums = new ArrayList<>();
        String s;
        Complex c = null;
        do {
            s = line.next();
            c = Complex.parseComplex(s);
            nums.add(c);
        } while (line.hasNext());
        Complex[] ret = new Complex[nums.size()];
        int i = 0;
        for (Complex a: nums) {
            ret[i] = a;
            i++;
        }
        return ret;
    }

    @Override
    public String toString() {
        String retStr = "";
        for (Complex[] row: this.mtx) {
            retStr += "|";
            for (Complex c: row) {
                retStr += c + "  ";
            }
            retStr = retStr.substring(0, retStr.length() - 2);
            retStr += "|\n";
        }

        return retStr;
    }
}
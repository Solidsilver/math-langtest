import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {
    private Complex[][] mtx;
    private int m, n;

    private Matrix(Complex[][] mtx, int m, int n) {
        this.mtx = mtx;
        this.m = m;
        this.n = n;
    }

    private Matrix(Complex[][] mtx) {
        this(mtx, mtx.length, mtx[0].length);
    }

    public Matrix() {
        Matrix newMtx = readMatrix();
        this.mtx = newMtx.mtx;
        this.m = newMtx.m;
        this.n = newMtx.n;
    }

    public int getM() {
        return this.m;
    }

    public int getN() {
        return this.n;
    }

    public Complex getValue(int row, int column) {
        if (row >= m || column >= n) {
            throw new IndexOutOfBoundsException();
        }
        return this.mtx[row][column];
    }

    public void setValue(Complex value, int row, int column) {
        if (row >= m || column >= n) {
            throw new IndexOutOfBoundsException();
        }
        this.mtx[row][column] = value;
    }

    public Matrix add(Matrix addand) {
        Complex[][] newMtr = new Complex[m][n];
        if (this.m != addand.m || this.n != addand.n) {
            throw new MismatchDimensionException("Addition requres the same dimension");
        }
        for (int row = 0; row < this.m; row++) {
            for (int x = 0; x < this.n; x++) {
                newMtr[row][x] = this.mtx[row][x].add(addand.mtx[row][x]);
            }
        }
        return new Matrix(newMtr);
    }

    public Matrix multiply(double multand) {
        Complex[][] newMtr = new Complex[m][n];
        for (int row = 0; row < this.m; row++) {
            for (int x = 0; x < this.n; x++) {
                newMtr[row][x] = this.mtx[row][x].multiply(multand);
            }
        }
        return new Matrix(newMtr);
    }

    public Matrix multiply(Matrix multand) {
        if (this.n != multand.m) {
            throw new MismatchDimensionException("Multiplication requre this.n = multand.m");
        }
        Complex[][] newMtr = new Complex[this.n][multand.m];
        for (int row = 0; row < this.n; row++) {
            for (int x = 0; x < multand.m; x++) {
                //TODO impliment multiplication.
            }
        }
        return new Matrix(newMtr);
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
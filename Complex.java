import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(double real) {
        this(real, 0.0);
    }

    public double getReal() {
        return this.real;
    }

    public double getImaginary() {
        return this.imaginary;
    }

    public double abs() {
        return Math.sqrt(
            Math.pow(this.real, 2) + Math.pow(this.imaginary, 2)
        );
    }

    public Complex add(Complex addend) {
        return new Complex(
            this.real + addend.real,
            this.imaginary + addend.imaginary
        );
    }

    public Complex add(double addend) {
        return new Complex(
            this.real + addend,
            this.imaginary
        );
    }

    public Complex subtract(Complex subtrand) {
        return new Complex(
            this.real - subtrand.real,
            this.imaginary = subtrand.imaginary
        );
    }

    public Complex subtract(double subtrand) {
        return new Complex(
            this.real - subtrand,
            this.imaginary
        );
    }

    public Complex conjugate() {
        return new Complex(
            this.real,
            this.imaginary * -1
        );
    }

    public Complex divide(Complex divisor) {
        double denomiinator = Math.pow(divisor.real, 2) + Math.pow(divisor.imaginary, 2);
        return new Complex(
            ((this.real * divisor.real) + (this.imaginary * divisor.imaginary))/denomiinator,
            ((this.imaginary * divisor.real) - (this.real * divisor.imaginary))/denomiinator
        );
    }

    public Complex multiply(Complex factor) {
        return new Complex(
            (this.real * factor.real) - (this.imaginary * factor.imaginary),
            (this.real * factor.imaginary) + (this.imaginary * factor.real)
        );
    }

    public Complex multiply(double factor) {
        return new Complex(
            this.real * factor,
            this.imaginary * factor
        ); 
    }

    public Complex pow(double x) {
        if (x < 1) {
            throw new IllegalArgumentException("Degree must be greater than 0. Was: " + x);
        }
        Complex retComp = new Complex(this.real, this.imaginary);
        for (int i = 1; i < x; i++) {
            retComp = retComp.multiply(this);
        }
        return retComp;
    }

    @Override
    public String toString() {
        String retString = this.real + "";
        if (this.imaginary > 0) {
            retString += "+";
        } else if (this.imaginary < 0) {
            retString += "-";
        }
        if (this.imaginary != 0) {
            if (Math.abs(this.imaginary) == 1.0) {
                retString += "i";
            } else {
                retString += Math.abs(this.imaginary) + "i";
            }
        } 
        return retString;
    }

    public static Complex parseComplex(String s) {
        Pattern p = Pattern.compile("[+-]?[0-9]+i");
        Matcher m = p.matcher(s);
        int indexI = s.indexOf("i");
        if (indexI < 0) {
            return new Complex(Double.parseDouble(s));
        }
        String real = s.replaceAll("[+-]?[0-9]+i", "");
        if (real.equals("")) {
            real = "0";
        }
        String imaginary;
        if (real.equals("i")) {
            real = "0";
            imaginary = "1";
        } else if (real.equals("-i")) {
            real = "0";
            imaginary = "-1";
        } else {
            if (m.find()) {
                imaginary = m.group(0).replaceAll("i", "");
                if (imaginary.equals("i")) {
                    imaginary = "1";
                }
            } else {
                System.out.println("No match");
                imaginary = "0";
            }
        }
        return new Complex(Double.parseDouble(real), Double.parseDouble(imaginary));
    }

    public static void main(String[] args) {
        System.out.println("Complex class operations test:");
        Complex c1 = new Complex(3, -2);
        Complex c2 = new Complex(3, 2);
        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c1 + c2: " + c1.add(c2));
        System.out.println("c1 * c2: " + c1.multiply(c2));
        System.out.println("c1^2: " + c1.pow(2));
        System.out.println("c1^5: " + c1.pow(5));

    }
}
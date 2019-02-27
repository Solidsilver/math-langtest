import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class {@code Complex} represents a complex number
 * as an object. An object of type {@code Complex}
 * contains a two fields of type {@code double} representing
 * the real and imaginary components of a complex number.
 * 
 * <p>This class also contains contains methods for 
 * performing basic numeric operations on {@code Complex} objects.
 * 
 * <p>In addition, this class provides methods for converting
 * a {@code Complex} to a {@code String} and a {@code String} to an
 * {@code Complex}, as well as other constants and methods useful when
 * dealing with a {@code Complex} number.
 * <p>
 * 
 * @author  Luke Mattfeld
 * @since   1.0
 */
public class Complex {
    private double real;
    private double imaginary;

    /**
     * The Complex constant 0+0i.
     *
     */
    public static Complex ZERO = new Complex(0);

    /**
     * The Complex constant 1+0i.
     *
     */
    public static Complex ONE = new Complex(1);

    /**
     * The Complex constant i.
     *
     */
    public static Complex I = new Complex(0, 1);

    /**
     * Constructs a newly allocated {@code Complex} object that
     * represents a complex number as
     * two {@code double} values.
     *
     * @param   real   the value of the real part of the complex number.
     * @param   imaginary the value of the imaginary part of the complex number.
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Constructs a newly allocated {@code Complex} object that
     * represents a complex number as
     * two {@code double} values.
     * 
     * This constructor sets the imaginary part to {@code 0};
     *
     * @param   real   the value of the real part of the complex number.
     */
    public Complex(double real) {
        this(real, 0.0);
    }

    /**
     * Returns the real part of {@code this} complex
     * as a {@code double}
     */
    public double getReal() {
        return this.real;
    }

    /**
     * Returns the imaginary part of {@code this} complex
     * as a {@code double}
     */
    public double getImaginary() {
        return this.imaginary;
    }

    /**
     * Returns a Complex whose value is the absolute value of {@code this} complex number.
     * This is defined as: <p>
     * abs(a + bi) = sqrt(a^2+b^2)
     *
     * @return {@code abs(this)}
     */
    public double abs() {
        return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
    }

    /**
     * Returns a Complex whose value is {@code (this + addend)}.
     * Uses the formula: <p>
     * (a + bi) + (c + di) = (a+c) + (b+d)i
     *
     * @param  addend complex value to be added to this Complex.
     * @return {@code this + addend}
     */
    public Complex add(Complex addend) {
        return new Complex(this.real + addend.real, this.imaginary + addend.imaginary);
    }

    /**
     * Returns a Complex whose value is {@code (this + addend)}.
     * Uses the formula: <p>
     * (a + bi) + c = (a+c) + bi
     *
     * @param  addend double value to be added to this Complex.
     * @return {@code this + addend}
     */
    public Complex add(double addend) {
        return new Complex(this.real + addend, this.imaginary);
    }

    /**
     * Returns a Complex whose value is {@code (this + subtrand)}. 
     * Uses the formula: <p>
     * (a + bi) - (c-di) = (a-c) + (b-d)i
     *
     * @param  subtrand complex value to be subtracted from this Complex.
     * @return {@code this + subtrand}
     */
    public Complex subtract(Complex subtrand) {
        return new Complex(this.real - subtrand.real, this.imaginary - subtrand.imaginary);
    }

    /**
     * Returns a Complex whose value is {@code (this - addend)}.
     * Uses the formula: <p>
     * (a + bi) - c = (a-c) + bi
     *
     * @param  addend double value to be added to this Complex.
     * @return {@code this - addend}
     */
    public Complex subtract(double subtrand) {
        return new Complex(this.real - subtrand, this.imaginary);
    }

    /**
     * Returns a Complex whose value is the complex conjugate of {@code this}.
     *
     * @return {@code (this.real) + (this.imaginary * -1)}
     */
    public Complex conjugate() {
        return new Complex(this.real, this.imaginary * -1);
    }

    /**
     * Returns a Complex whose value is {@code (this / divisor)}.
     * Uses the formula: <p>
     *
     * (a + bi) / (c + di) = 
     * (((a*c)+(b*d))/(c^2+d^2) +
     * (((b*c)-(a*d))/(c^2+d^2))i
     *
     *
     * @param  divisor Complex value to divide this Complex by.
     * @return {@code this / divisor}
     */
    public Complex divide(Complex divisor) {
        double denominator = Math.pow(divisor.real, 2) + Math.pow(divisor.imaginary, 2);
        return new Complex(((this.real * divisor.real) + (this.imaginary * divisor.imaginary)) / denominator,
                ((this.imaginary * divisor.real) - (this.real * divisor.imaginary)) / denominator);
    }

    /**
     * Returns a Complex whose value is {@code (this * factor)}.
     * Uses the formula: <p>
     * (a + bi) * (c + di) = (a*c - b*d) + (a*d + b*c)i
     *
     * @param  factor Complex value to be multiplied by this Complex.
     * @return {@code this * factor}
     */
    public Complex multiply(Complex factor) {
        return new Complex((this.real * factor.real) - (this.imaginary * factor.imaginary),
                (this.real * factor.imaginary) + (this.imaginary * factor.real));
    }

    /**
     * Returns a Complex whose value is {@code (this * factor)}.
     * Uses the formula: <p>
     * (a + bi) * c = (a*c) + (b*c)i
     *
     * @param  factor double value to be multiplied by this Complex.
     * @return {@code this * factor}
     */
    public Complex multiply(double factor) {
        return new Complex(this.real * factor, this.imaginary * factor);
    }

    /**
     * Returns a Complex whose value is {@code (this ^ x)}.
     * Uses the formula: <p>
     * (a+bi)^n = (a+bi)(a+bi)...(a+bi) - n times;
     *
     * @param  x double value to raise this Complex by.
     * @return {@code this ^ x}
     */
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

    /**
     * Returns a {@code String} object representing this
     * {@code Complex}'s value. The value is converted to signed
     * complex-decimal representation and returned as a string, exactly as if
     * the real and imaginary values were given as an argument to the {@link
     * java.lang.Double#toString(double)} method, with appropriate sign
     * between parts, and an ASCII {@code 'i'} after the imaginary part.
     * 
     * If there is no imaginary part, only the real part will be returned.
     * If there is no real part, only the imaginary part will be returned.
     *
     * @return  a string representation of the value of this object inf the form
     * {@code a + bi}, where {@code a} is the real part and {@code b} is the imaginary part.
     * @see java.lang.Double#toString(double)
     */
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

    /**
     * Parses the string argument as Complex number. The
     * characters in the string must all be decimal digits, except
     * that the character before the real and imaginary parts 
     * may be an ASCII minus sign {@code '-'}
     * ({@code '\u005Cu002D'}) to indicate a negative value or an
     * ASCII plus sign {@code '+'} ({@code '\u005Cu002B'}) to
     * indicate a positive value, and an ASCII {@code 'i'}
     * after the imaginary part to indicate
     * it is imaginary. The resulting Complex value is
     * returned.
     *
     * @param s    a {@code String} containing the {@code Complex}
     *             representation to be parsed
     * @return     the Complex value represented by the argument.
     * @exception  NumberFormatException  if the string does not contain a
     *               parsable Complex.
     */
    public static Complex parseComplex(String s) throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("null");
        }
        s.strip();
        if (s.contains(" ")) {
            throw new NumberFormatException("Cannot contain spaces");
        }
        Pattern p = Pattern.compile("[+-]?([0-9]+)?i");
        Matcher m = p.matcher(s);
        String imaginary;
        String real;
        real = s.replaceAll("[+-]?([0-9]+)?i", "");
        if (real.equals("")) {
            real = "0";
        }
        if (m.find()) {
            imaginary = m.group(0).replaceAll("i", "");
            if (imaginary.equals("+") || imaginary.equals("-") || imaginary.equals("")) {
                imaginary += "1";
            }
        } else {
            imaginary = "0";
        }
        try {
            return new Complex(Double.parseDouble(real), Double.parseDouble(imaginary));
        } catch (Exception e) {
            throw new NumberFormatException("There was a parsing error.");
        }
        
    }
}
package languages.jmath;

import java.math.BigDecimal;

public class BigComplex {
    private BigDecimal real;
    private BigDecimal imaginary;

    public BigComplex(double real) {
        this(real, 0);
    }

    public BigComplex(double real, double imaginary) {
        this.real = BigDecimal.valueOf(real);
        this.imaginary = BigDecimal.valueOf(imaginary);
    }

    private BigComplex(BigDecimal real, BigDecimal imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public BigDecimal getReal() {
        return this.real;
    }

    public BigDecimal getImaginary() {
        return this.imaginary;
    }

    public BigComplex add(BigComplex c) {
        return new BigComplex(
                c.getReal().add(this.real),
                c.getImaginary().add(this.imaginary));
    }

    public BigComplex subtract(BigComplex c) {
        return new BigComplex(
                c.getReal().subtract(this.real),
                c.getImaginary().subtract(this.imaginary));
    }

    public BigComplex multiply(BigComplex c) {
        return new BigComplex(
                (this.real.multiply(c.real)).subtract(this.imaginary.multiply(c.imaginary)),
                (this.real.multiply(c.imaginary)).add(this.imaginary.multiply(c.real)));
    }

    public BigComplex divide(BigComplex c) {
        return new BigComplex(
                ((this.real.multiply(c.real)).add(this.imaginary.multiply(c.imaginary))
                        .divide((c.real.pow(2)).add(c.imaginary.pow(2)))),
                ((((this.imaginary).multiply(c.real)).subtract((this.real).multiply(c.imaginary)))
                        .divide((c.real.pow(2)).add(c.imaginary))));
    }

    public BigComplex conjugate() {
        return new BigComplex(this.real, this.imaginary.multiply(BigDecimal.valueOf(-1)));
    }

}
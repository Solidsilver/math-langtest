package lib

import (
	"errors"
	"math"
)

type Complex struct {
	Real, Imag float64
}

func C_Zero() Complex {
	return Complex{0, 0}
}

func C_One() Complex {
	return Complex{1, 0}
}

func C_I() Complex {
	return Complex{0, 1}
}

func (c Complex) Abs() float64 {
	return math.Hypot(c.Real, c.Imag)
}

func (c Complex) Add(o Complex) Complex {
	return Complex{c.Real + o.Real, c.Imag + o.Imag}
}

func (c *Complex) SetAdd(a Complex, b Complex) {
	c.Real = a.Real + b.Real
	c.Imag = a.Imag + b.Imag
}

func (c Complex) Subtract(o Complex) Complex {
	return Complex{c.Real - o.Real, c.Imag - o.Imag}
}

func (c *Complex) SetSubtract(a Complex, b Complex) {
	c.Real = a.Real - b.Real
	c.Imag = a.Imag - b.Imag
}

func (c Complex) Conjugate() Complex {
	return Complex{c.Real, c.Imag * -1}
}

func (c Complex) Multiply(fact Complex) Complex {
	return Complex{
		(c.Real * fact.Real) - (c.Imag * fact.Imag),
		(c.Real * fact.Imag) + (c.Imag * fact.Imag),
	}
}

func (c Complex) MultiplyFloat(fact float64) Complex {
	return Complex{
		c.Real * fact,
		c.Imag * fact,
	}
}

func (c *Complex) SetMultiply(a Complex, b Complex) {
	c.Real = (a.Real * b.Real) - (a.Imag * b.Imag)
	c.Imag = (a.Real * b.Imag) + (a.Imag * b.Imag)
}

func (c Complex) Divide(div Complex) Complex {
	denominator := math.Pow(div.Real, 2) + math.Pow(div.Imag, 2)

	return Complex{
		((c.Real * div.Real) + (c.Imag * div.Imag)) / denominator,
		((c.Imag * div.Real) - (c.Real * div.Imag)) / denominator,
	}
}

func (c *Complex) SetDivide(a Complex, b Complex) {
	denominator := math.Pow(b.Real, 2) + math.Pow(b.Imag, 2)
	c.Real = ((a.Real * b.Real) + (a.Imag * b.Imag)) / denominator
	c.Imag = ((a.Imag * b.Real) - (a.Real * b.Imag)) / denominator
}

func (c Complex) Negate() Complex {
	return Complex{
		c.Real * -1,
		c.Imag * -1,
	}
}

func (c *Complex) SetNegate() {
	c.Real *= -1
	c.Imag *= -1
}

func C_Pow(c Complex, x int) (Complex, error) {
	if x < 0 {
		return C_Zero(), errors.New("degree must be greater than 0")
	}
	if x == 0 {
		return C_One(), nil
	}
	retComp := Complex{c.Real, c.Imag}
	for i := 1; i < x; i++ {
		retComp.SetMultiply(retComp, retComp)
	}
	return retComp, nil
}

func (c Complex) Pow(x int) (Complex, error) {
	if x < 0 {
		return C_Zero(), errors.New("degree must be greater than 0")
	}
	if x == 0 {
		return C_One(), nil
	}
	retComp := Complex{c.Real, c.Imag}
	for i := 1; i < x; i++ {
		retComp.SetMultiply(retComp, retComp)
	}
	return retComp, nil
}

func (c *Complex) SetPow(x int) {
	if x >= 0 {
		if x == 0 {
			c.Real = 1
			c.Imag = 0
		} else {
			c.SetMultiply(*c, *c)
		}
	}
}

func (c Complex) Reciprocal() Complex {
	return C_One().Divide(c)
}

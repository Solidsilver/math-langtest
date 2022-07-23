package main

import (
	"fmt"
	"gomath/lib"
)

func main() {
	println("hello, world")

	c := lib.Complex{Real: 5, Imag: -3}
	// c2 := lib.Complex{Real: 5, Imag: 4}
	cRes := lib.C_One()

	cRes, _ = c.Pow(3)

	fmt.Printf("%+v\n", cRes)
}

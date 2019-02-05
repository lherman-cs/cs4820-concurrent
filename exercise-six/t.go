package main

import (
	"bytes"
	"fmt"
	"os/exec"
)

var expected = `MESSAGE RECEIVED: Mares eat oats
MESSAGE RECEIVED: Does eat oats
MESSAGE RECEIVED: Little lambs eat ivy
MESSAGE RECEIVED: A kid will eat ivy too
`

func main() {
	var c int
	for {
		fmt.Println(c)
		var buff bytes.Buffer
		cmd := exec.Command("java", "ProducerConsumerExample")
		cmd.Stdout = &buff
		cmd.Run()

		if buff.String() != expected {
			fmt.Println(buff.String())
			break
		}
		c++
	}
}

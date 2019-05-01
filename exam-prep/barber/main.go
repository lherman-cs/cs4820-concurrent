package main

import (
	"fmt"
	"math/rand"
	"time"
)

func shop(workers []chan<- int) chan<- int{
	source := make(chan int)
	
	for _, worker := range workers {
		go func(w chan<- int){
			for msg := range source {
				w <- msg
			}
		}(worker)
	}
	return source
} 

func createWorker(i int) chan<- int {
	source := make(chan int)
	go func(){
		for msg := range source {
			fmt.Println(i, msg)
		}
	}()
	return source
}

func main(){
	nWorkers := 4
	workers := make([]chan<- int, nWorkers)

	for i := 0; i < nWorkers; i++ {
		workers[i] = createWorker(i)
	} 

	s := shop(workers)
	for {
		s <- rand.Intn(1000)	
		time.Sleep(1)
	}
}
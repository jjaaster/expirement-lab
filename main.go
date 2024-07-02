package main

import (
	"fmt"
	"sync"
)

var wg sync.WaitGroup

func main() {
	wg.Add(2)

	go func() {
		fmt.Println("sequential consistency 1")
		wg.Done()
	}()

	go func() {
		fmt.Println("sequential consistency 2")
		wg.Done()
	}()

	wg.Wait()
}

package main

import (
	"fmt"
	"log"
	"net"
	"net/http"
)

func main() {
	http.HandleFunc("/hello", helloHandler)
	l, err := net.Listen("tcp", "localhost:0")
	if err != nil {
		panic(err)
	}
	c := make(chan bool)
	go func() {
		if err := http.Serve(l, nil); err != nil {
			panic(err)
		}
		c <- true
	}()
	fmt.Println(l.Addr())
	<-c
}

func helloHandler(w http.ResponseWriter, r *http.Request) {
	log.Print("hello")
	fmt.Fprintf(w, "hello")
}

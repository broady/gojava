gojava
======

Proof of concept demonstrating how to call Go from Java.

Source files of interest:
* [Main.java](https://github.com/broady/gojava/blob/master/src/main/java/gojava/Main.java)
* [main.go](https://github.com/broady/gojava/blob/master/src/main/go/main.go)

Packaging:
* Go binary is included in the jar file.

At runtime:
* From Java, Go binary is extracted to a temporary directory
* From Java, the Go binary is executed
* From Go, an HTTP server is started on localhost. The address is printed to stdout.
* From Java, address is read from stdout and HTTP requests can be made to the Go program.

To run the sample, which prints out the temporary file location and response body from the HTTP request:

    $ ./gradlew jar && java -jar build/libs/gojava-1.0.jar
    /var/folders/00/0wz20000h01000cxqpysvccm003lw8/T/goprogram7559274531365356488.tmp
    from golang: hello

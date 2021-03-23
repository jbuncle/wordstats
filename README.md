# Word Stats Analyser

An illustrative/demonstrative/boilerplate Java based Rest API and CLI for 
analysing word lengths in a given text.

A "word" is a string of characters that are alphanumeric, &, £, $, ' (apostrophe)
or slashes (/ and \).

## Running

There are a number of commands available through the `./local-dev.sh` utility 
script, otherwise you can run the Rest API (on http://localhost:8080/) using the commands below.

### Run with Maven

```bash
mvn spring-boot:run
```

### Run with Docker

```bash
docker build -t wordstats .
docker run --rm -it -p8080:8080 wordstats
```



## Code Structure/Design

The code has been structured in a way to demonstrate a number of Java related 
practices, including:

 - SOLID Principles (though not exhaustive)
 - Unit Testing with JUnit, Parameterized Tests, Test Stubs with Mockito & Code Coverage with JaCoCo
 - Automated code quality analysis & reporting (with checkstyle and pmd)
 - Multi-threading and concurrency
 - Use of the Spring Framework for a Rest API with Swagger UI - inc. Autowiring dependencies
 - CLI
 - Regular expressions
 - Exception handling
 - Maven & Docker
 - Bash Scripting (in form of local-dev.sh utility script)


As this project is for illustrative/demonstrative purposes, the resulting code is 
disproportionate to the problem being solved.

In this case the CLI has been bundled into Web application, where it would 
normally have 2 separate applications sharing code from a library.


## Performance

Implemented in a way that would support multi-threading (keeping objects immutable where appropriate and using thread safe types), although not currently utilising

The design is intended to be fast and high performance, because the size of the text payload may be large, 
the processing occurs as the text is read, avoiding consuming memory by storing large texts in it.


## Code Quality

This project includes:
 - JUnit & Mockito tests (run with `mvn test`)
 - JaCoCo for unit test coverage
 - Checkstyle using a variant of sun_checks.xml (run with `mvn checkstyle:checkstyle`, results in `target/site/checkstyle.html`)
 - Programming Mess Detection (run with `mvn pmd:check` results in `target/pmd.xml`)

#! /bin/bash
set -e

check() {
    mvn checkstyle:checkstyle
    mvn pmd:check
    mvn test
}
build() {
    mvn checkstyle:checkstyle install
}

build-docker() {
    docker build -t wordstats .
}
run-docker() {
    docker run --rm -it -p8080:8080 wordstats
}
build-run-docker() {
    build-docker
    run-docker
}

#
# Run CLI
#
run-cli() {
    mvn package
    java -classpath target/wordstats-0.0.1-SNAPSHOT.war uk.co.jbuncle.wordstats.cli.MainCli "$@"

}

#
# Package and run from jar
#
run-jar() {
    mvn package
    java -jar target/wordstats-0.0.1-SNAPSHOT.war
}

#
# Run Web App
#
run() {
    mvn spring-boot:run
}

case $1 in
    "check")
        check
    ;;
    "build")
        build
    ;;
    "build-run-docker")
        build-run-docker
    ;;
    "run")
        run
    ;;
    "run-cli")
        run-cli "${@:1}"
    ;;
    "run-jar")
        run-jar
    ;;
    *)
        echo "Specify 'check', 'build', 'run', 'run-cli', 'build-run', 'build-run-docker'"
    ;;
esac


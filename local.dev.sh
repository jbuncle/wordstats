#! /bin/bash



build() {
    mvn clean package
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
    "build")
        build
    ;;
    "run")
        run
    ;;
    "run-jar")
        run-jar
    ;;
    *)
        echo "Specify 'build', 'run', 'build-run'"
    ;;
esac


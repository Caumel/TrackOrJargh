#!/bin/bash

path_project=$(dirname $(pwd))/TrackOrJargh
path_jar=$path_project/target

#Create jar TrackOrjargh
docker run -it --rm --name trackorjargh -v "$path_project":/usr/src/mymaven -w /usr/src/mymaven maven mvn package -DskipTests

#Move jar to actual directory
mv $path_jar/TrackOrJargh-0.0.1-SNAPSHOT.jar .

#Create image 
docker build -t oscarsotosanchez/trackorjargh-spring .

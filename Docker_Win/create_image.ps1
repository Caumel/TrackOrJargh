$var = $(get-item ${PWD}).parent.FullName
$pathProject = $var + "\TrackOrJargh"
$pathJar = $pathProject + "\target"

#Create jar TrackOrjargh
docker run -it --rm --name trackorjargh -v ${pathProject}:/usr/src/mymaven -w /usr/src/mymaven maven mvn package -DskipTests

#Move jar to actual directory
mv ${pathJar}/TrackOrJargh-0.0.1-SNAPSHOT.jar .

#Create image 
docker build -t trackorjargh .


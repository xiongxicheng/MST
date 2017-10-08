#!/bin/bash

cd src
javac *.java
graphFiles=`ls ../data/ | grep .gr`

for graph in $graphFiles
do
	filename=`echo $graph | cut -d'.' -f1`
	echo $graph $filename
	touch ../results/$filename'_output'.txt
	java RunExperiments ../data/$graph ../data/$filename.extra ../results/$filename'_output'.txt >> output.txt


done
cd ..

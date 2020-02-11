#!/bin/bash

#cd "original"
#javac *.java
#cd - > /dev/null
touch results.txt
declare -a filenames
index=0
for f in submissions/*; do
    filenames[$index]="$f"
    ((++index))
    #echo $f
done

mkdir extract
mkdir error
for currentfile in "${filenames[@]}"; do
unzip "$currentfile" -d "extract" > /dev/null
rm -f "$currentfile"
cd extract
if [ ! -f "SkipList.java" ] || [ ! -f "SingleLinkedList.java" ] || [ ! -f "ListComparison.java" ]; then
    echo "Source files not complete, moving to error/" 
    cd - > /dev/null
    cd error
    #echo $currentfile
    #echo "$(basename "$currentfile" ".zip")"
    mkdir "$(basename "$currentfile" ".zip")"
    mv -f ../extract/* -t "$(basename "$currentfile" ".zip")"
    cd - > /dev/null
else
    for f in *; do
        sed -i "s/package .*;/package test;/" $f
    done
cd - > /dev/null
mv extract/* -t test > /dev/null
javac *.java > /dev/null
javac */*.java > /dev/null
echo $currentfile >> results.txt
java HW2TestScript 2>> result.txt
cat result.txt | tee -a results.txt
echo "------------------------" >> results.txt
rm result.txt
rm test/* > /dev/null
echo $currentfile

fi
echo "Press enter to continue"
read dummy
done
rmdir extract
rm -f *.class 
rm -f */*.class

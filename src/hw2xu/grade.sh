###############################################################################
# Author: Max Shi
# Date: 2/10/2020
# Description: Grading scripts to automate testing of multiple files at once. Run once for startup instructions.
# Dependencies: unzip, javac, java
###############################################################################
#!/bin/bash

if [ ! -d "original" ] || [ ! -d "test" ] || [ ! -d "submissions" ]; then
    echo "original, submissions, and error directories will be created. Place original solution in original, and put test script java file in the same directory as this script."
    mkdir original > /dev/null
    mkdir submissions > /dev/null
    mkdir error > /dev/null
    exit 1
fi
if [ ! -f *.java ]; then
    echo "Test script not found!"
    exit 1
fi
touch results.txt
declare -a filenames
declare -a origfiles
index=0
for f in submissions/*; do
    filenames[$index]="$f"
    ((++index))
    #echo $f
done
index=0
for f in original/*.java; do
    origfiles[$index]="$(basename "$f" ".zip")"
    ((++index))
done

mkdir extract > /dev/null
mkdir test > /dev/null

for currentfile in "${filenames[@]}"; do
    unzip "$currentfile" -d "extract" > /dev/null
    rm -f "$currentfile"
    cd extract
    valid_files=1
    for orig in "${origfiles[@]}"; do
        if [ ! -f "$orig" ]; then
        echo "Submission source files not complete, moving to error/$(basename "$currentfile" ".zip")" 
        cd - > /dev/null
        cd error
        mkdir "$(basename "$currentfile" ".zip")"
        mv -f ../extract/* -t "$(basename "$currentfile" ".zip")"
        cd - > /dev/null
        valid_files=0
        break
        fi
    done
    
    if [ $valid_files -eq 1 ]; then
        for f in *; do
            sed -i "s/package .*;/package test;/" $f
        done
        cd - > /dev/null
        mv extract/* -t test > /dev/null
        javac *.java > /dev/null
        javac */*.java > /dev/null
        echo $currentfile >> results.txt
        java HW2TestScript 2> result.txt
        cat result.txt | tee -a results.txt
        echo "------------------------" >> results.txt
        rm -f result.txt
        rm test/* > /dev/null
        echo $currentfile
    fi
    echo "Press enter to continue"
    read dummy
done
rmdir extract
rmdir test
rm -f *.class 
rm -f */*.class
exit 0
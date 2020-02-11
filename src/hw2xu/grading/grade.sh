# Grading script written by Max Shi, 2/10/2020
#!/bin/bash

if [ ! -d "original" ] || [ ! -d "test" ] || [ ! -d "submissions" ]; then
    echo "original, submissions, and test directories will be created. Place original solution in original, and put test script java file in the same directory as this script."
    mkdir original
    mkdir test
    mkdir submissions
    exit 1
fi
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
    echo "Source files not complete, moving to error/$(basename "$currentfile" ".zip")" 
    cd - > /dev/null
    cd error
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
exit 0
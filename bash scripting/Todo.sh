#!/bin/bash

if [ ! -d "errors" ] || [ ! -d "no_duedates" ]
then
    echo "Folders errors and no_duedates do not exist in the current directory. Check you are in the files directory">&2
    return 1
fi

# -_-_-_-_-_-_-_-_-_-_-_-
ProjectMatchesTitle() {
# Get file name without extention
# Get parameter passed to file in ./ProjectTitleMatches.sh Data.md the parameter would be Data.md
inputFile=$1
filenameWithExtension=${inputFile##*/} # (1)
filenameNoExtension=${filenameWithExtension%.*}
# Example
# > echo $filenameWithExtension
# > Data.md
# > echo $filenameNoExtension
# > Data


# Get project title
headLine=$(head -n 1 $inputFile)
# Remove the '#'
titlePart=${headLine[@]:1}


if [ "$filenameNoExtension" == "$titlePart" ]
then
    : # The filename an title match. All is well
else

    cp $inputFile ./errors
fi


#(1) I got help with file name filtering here >> https://stackoverflow.com/questions/3362920/get-just-the-filename-from-a-path-in-a-bash-script
# -_-_-_-_-_-_-_-_-_-_-_-
}


# -_-_-_-_-_-_-_-_-_-_-_-

CheckForDueDate() {

tempCorrect=""
tempWrong=""

while read line
do
    # check if the first line is a '#' (Which means it isn't a todo line
    if [ "#" == "${line[@]:0:1}" ]
    then
       tempCorrect+="$line\n"
       tempWrong+="$line\n"
       continue
    fi

    #check if the line contains the word due
    if [[ "$line" =~ due ]]
    then
        tempCorrect+="$line\n"

    else
        tempWrong+="$line\n"
    fi


done < $1

#Update contents of the input file
echo -e "$tempCorrect" > $1
touch ./no_duedates/$1
echo -e "$tempWrong" > ./no_duedates/$1
}



projectFiles=$(find . | grep md)

for file in $projectFiles
do
    ProjectMatchesTitle "$file"
done

echo "" > combinedProjects.md
projectFiles=$(find . -type f -not -path "*errors*" -not -path "*no_duedates*" | grep -v combinedProjects | grep md)
for file in $projectFiles
do
    cat "$file" >> combinedProjects.md
done


CheckForDueDate combinedProjects.md


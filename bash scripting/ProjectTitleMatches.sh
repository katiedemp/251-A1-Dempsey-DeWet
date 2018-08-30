#!/bin/bash

#Check project title matches the file name and moves files that don't match to a directory

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
    echo "$filenameWithExtension matches project name of $headLine"
else
    echo "$filenameWithExtension does not match project name of $headLine"
    errorPath=./error_files
    # Make directory 'error_files' redirect the error message to the void if it already exists
    mkdir error_files 2> /dev/null
    echo "Moving $filenameWithExtension to $errorPath"
    cp $inputFile $errorPath
fi


#(1) I got help with file name filtering here >> https://stackoverflow.com/questions/3362920/get-just-the-filename-from-a-path-in-a-bash-script

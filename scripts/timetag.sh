#!/bin/bash
while read line ; do
    #echo `date +%s` $line
    echo `date +"%s.%6N"` $line
    #echo "$(($(date +%s%N)/1000000)) $line"
done


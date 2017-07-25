#!/bin/bash
gcc -o fft fft.c -lfftw3 -lm
gcc -o lpf lpf.c 
gcc -o clean clean.c -lm
gcc -o filter_channel filter_channel.c -lm
gcc -o timetag timetag.c
javac FilterChannel.java Clean.java Resample.java


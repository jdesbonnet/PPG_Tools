#!/bin/bash
gcc -o fft fft.c -lfftw3 -lm
gcc -o lpf lpf.c 
gcc -o clean clean.c -lm
gcc -o filter_channel filter_channel.c -lm
javac FilterChannel.java Clean.java


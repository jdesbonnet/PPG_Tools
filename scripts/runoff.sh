#!/bin/bash
SPS=100
FFT_SIZE=4096
gunzip -c ../data/20170723/overnight.ppg.gz | java FilterChannel 0 |  tail -n +100000 | java Clean | cut -d ' ' -f 1,6,7 | ./fft ${FFT_SIZE} ${SPS} > t.matrix
gnuplot plot_waterfall.gnuplot



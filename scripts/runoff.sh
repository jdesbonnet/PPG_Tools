#!/bin/bash
SPS=100
FFT_SIZE=4096
#gunzip -c ../data/20170723/overnight.ppg.gz | java FilterChannel 0 |  tail -n +100000 | java Clean | cut -d ' ' -f 1,6,7 | ./fft ${FFT_SIZE} ${SPS} > t.matrix
#gnuplot plot_waterfall.gnuplot


gunzip -c ../../PPG_Data/data/20170725/ppg2.dat.gz | java CleanLevel1 | java FilterChannel 0 | java DeHex -t 1501040224 | java HighPassFilter | java CleanLevel2 -x 2000 > t.0
gunzip -c ../../PPG_Data/data/20170725/ppg2.dat.gz | java CleanLevel1 | java FilterChannel 1 | java DeHex -t 1501040224 | java HighPassFilter | java CleanLevel2 -x 2000 > t.1




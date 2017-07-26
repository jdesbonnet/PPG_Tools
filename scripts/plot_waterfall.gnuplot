
set terminal pngcairo size 1024,600
set output 'ppg_spectrum.png'


set title "PPG signal spectrum plot recorded over night. (100sps, 4096 sample FFTs)"
set xlabel "Time (seconds)"
set ylabel "Frequency (Hz)"
set cblabel "Normalized DFT"

set cbrange [0:1]
set xrange [0:10000]
set yrange [0:5]

#set grid
#set logscale cb

set label 1 at  6800,1.4 "Heart beat" front textcolor "white"
set label 2 at  6800,0.5 "Respiration" front textcolor "white"

load "spectrum2.pal"

plot 't.matrix' using 2:1:($4/200000) with image
#pause -1

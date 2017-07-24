
set terminal pngcairo size 2048,800
set output 'ppg.png'

set yrange [1:256]
set cbrange [10:100000]
set logscale cb


plot 't.matrix' using 2:1:4 with image
#pause -1

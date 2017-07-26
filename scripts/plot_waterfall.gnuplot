
set terminal pngcairo size 2048,800
set output 'ppg.png'

set yrange [0:5]

set cbrange [1000:500000]
#set logscale cb

load "spectrum2.pal"

plot 't.matrix' using 2:1:4 with image
#pause -1

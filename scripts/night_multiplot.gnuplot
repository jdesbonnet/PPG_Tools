set terminal pngcairo size 1800,1000 background rgb 'black'
set output "night.png"


# Not possible to set textcolor in multiplot title
# Ref: https://stackoverflow.com/questions/38981511/how-to-specify-color-of-multiplot-title
# Use set label as workaround.
set multiplot layout 4, 1  title "Experiments with MAX30102 pulse oximeter sensor." font ",24"
set label 1 "Overnight recording of photoplethysmogram (PPG) using 2 x MAX30102 pulse oximeter sensors (A: thumb, B: toe)" \
center at graph 0.5,1.12 font ",18" tc rgb "white"


#set key box opaque
set border lc rgb "white"
set key tc rgb "white"


set grid linecolor rgb "white"
set style fill transparent solid 0.4 noborder
set style fill noborder


set tmargin 0.5
set bmargin 0.5
set lmargin 10

set xdata time
set timefmt "%s"

# Remove tic labels
set format x ""

set ytics font ",10"

# This does not work?!
#set xrange [1501298880:1501298880+10000]

circle_r = 10

#
# Plot 1
#
#unset key
set yrange [0:0.001]
plot \
'pulse.0' using 1:($2):(circle_r) title "Normalized PPG AC amplitude, red, sensor A" with circle, \
'' using 1:($3):(circle_r) title "Normalized PPG AC amplitude, NIR, sensor A" with circle, \
'pulse.1' using 1:($2):(circle_r) title "Normalized PPG AC amplitude, red, sensor B" with circle, \
'' using 1:($3):(circle_r) title "Normalized PPG AC amplitude, NIR, sensor B" with circle


unset label 1

#
# Plot 2
#
set yrange [50:110]
set ylabel "bpm" textcolor rgb "white"
plot 'pulse.0' using 1:(60/$4):(circle_r) title "heart rate (bpm)" with circles


#
# Plot 3
#
set yrange [80:100]
set ylabel "uncalibrated SPO₂ %"
plot \
'pulse.0' using 1:(110 - 25*$2/$3):(circle_r) title 'SPO₂% (A)' with circle, \
'pulse.1' using 1:(110 - 25*$2/$3):(circle_r) title 'SPO₂% (B)' with circle 

#
# Plot 4
#
set xlabel "Time" textcolor rgb "white"
set ylabel "seconds"
set yrange [0:0.4]
set xtics
set format x "%H:%M"
set bmargin 3


set label "Joe Desbonnet http://jdesbonnet.blogspot.com" at graph -0.05,-0.22 font ",10" tc rgb "white"
#set label "http://jdesbonnet.blogspot.com" at graph -0.05,-0.25 font ",10" tc rgb "white"


plot \
'phase.dat' using 1:2:(circle_r) title 'A/B sensor phase (seconds A leading B)' with circle



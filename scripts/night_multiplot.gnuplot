set terminal qt size 1800,1000

set multiplot layout 4, 1  
set key box opaque

set grid
set tmargin 0.5
set bmargin 0.5
set lmargin 10

set xdata time
set timefmt "%s"

# Remove tic labels
set format x ""

#
# Plot 1
#
#unset key
set yrange [0:1]
plot \
'pulse.0' using 1:($2*1000) title 'Normalized PPG AC Amplitude A' , \
'pulse.1' using 1:($2*1000) title 'Normalized PPG AC Amplitude B' 


#
# Plot 2
#
set yrange [50:110]
set ylabel "bpm"
plot 'pulse.0' using 1:(60/$4) title 'heart rate (bpm)' with lines


#
# Plot 3
#
set yrange [80:100]
set ylabel "%"
plot \
'pulse.0' using 1:(110 - 25*$2/$3) title 'SPO2% (A)' with lines, \
'pulse.1' using 1:(110 - 25*$2/$3) title 'SPO2% (B)' with lines

#
# Plot 4
#
set ylabel "seconds"
set yrange [0:0.4]
set xtics
set format x "%H:%M"
set bmargin 2
plot \
'phase.dat' using 1:2 with lines title 'A/B sensor phase (seconds)'

pause -1

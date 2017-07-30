set terminal qt size 1400,600

set multiplot layout 3, 1 title "PPG" 
set tmargin 1

set title "Normalized signal amplitude"
unset key
set yrange [0:1]
plot \
'pulse.0' using 1:($2*1000) title 'Normalized PPG AC Amplitude A' with lines, \
'pulse.1' using 1:($2*1000) title 'Normalized PPG AC Amplitude B' with lines

set title "Heart rate"
set yrange [50:110]
unset key
plot 'pulse.0' using 1:(60/$3) title 'heart rate (bpm)'


set title "A/B Phase and SPO2"
set yrange [0:0.4]
unset key
plot \
'phase.dat' using 1:2 with lines title 'A/B sensor phase (seconds)', \
'pulse.0' using 1:(200*$2/$3) title 'SPO2 (A)' with lines, \
'pulse.1' using 1:(200*$2/$3) title 'SPO2 (B)' with lines

pause -1

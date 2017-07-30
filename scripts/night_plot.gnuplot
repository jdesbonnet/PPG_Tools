set terminal qt size 1400,600
set yrange [0:1]
plot 'pulse.0' using 1:($2*1000) title 'Normalized PPG AC Amplitude A', \
'pulse.1' using 1:3 title 'period (seconds)', \
'pulse.1' using 1:($2*1000) title 'Normalized PPG AC Amplitude B', \
'phase.dat' using 1:2 with lines title 'A/B sensor phase (seconds)', \
'pulse.0' using 1:(200*$2/$3) title 'SPO2 (A)' with lines, \
'pulse.1' using 1:(200*$2/$3) title 'SPO2 (B)' with lines
pause -1

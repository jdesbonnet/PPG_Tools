#!/bin/bash
#
# Record PPG data from UART. Due to 
# On RaspberryPi with cheap UART cable observe the occasional
# disconnect. So wrap in loop and restart if the cat process
# is terminated.

UART="/dev/ttyUSB0"
BPS="230400"

# Condition UART for use with cat 
stty -F ${UART} speed ${BPS} -echo -icrnl

while true; do
    echo "# Starting UART record at `date` " >> ppg.dat
    cat /dev/ttyUSB0 | ./PPG_Tools/scripts/timetag >> ppg.dat

    echo "# cat process stopped at `date` " >> ppg.dat

    # Wait for UART to reappear if not immediately visible
    while [ ! -e ${UART} ]; do
        echo "# waiting for UART to reappear at `date`"	
        sleep 1
    done
done


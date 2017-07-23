#!/bin/bash

# Note: need to condition UART device with stty (speed etc) before cat
# of the device to pipe/file will work.

DELAY=$1
NREC=$2

sleep $DELAY ; cat /dev/ttyUSB0 | ./timetag.sh | head -n $NREC > ppg.dat


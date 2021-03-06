#!/bin/bash

#ZEROTIME="java ZeroTime -t 1501298880"
ZEROTIME="cat - "

gunzip -c ../../PPG_Data/data/20170729/ppg?.dat.gz | java CleanLevel1 | java FilterChannel 0 | java DeHex | $ZEROTIME | java CleanLevel2 > t.0 &
gunzip -c ../../PPG_Data/data/20170729/ppg?.dat.gz | java CleanLevel1 | java FilterChannel 1 | java DeHex | $ZEROTIME | java CleanLevel2 > t.1 &

wait

octave ppg_filter.m t.0 filtered.0 &
octave ppg_filter.m t.1 filtered.1 &

wait

java Pulse -x -5e-6 < filtered.0 > pulse.0 &
java Pulse -x -5e-6 < filtered.1 > pulse.1 &

wait

java Phase pulse.0 pulse.1 > phase.dat




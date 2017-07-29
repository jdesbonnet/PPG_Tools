#!/usr/bin/env octave -q
#
# Apply frequency filters transform to PPG sensor data
#
# Command line:
# octave  filter.m  ppg.dat > /dev/null
#
# Joe Desbonnet,
# jdesbonnet@gmail.com
# 29 July 2017


echo off

datain = argv(){1}
dataout = argv(){2}

data_matrix = dlmread(datain, " ");

red = data_matrix( : , 4);
nir = data_matrix( : , 5);
time = data_matrix ( : ,1);

Fs = 200;
Ts = 1/Fs;
Fn = Fs/2;

f1 = 0.5;
f2 = 10.0;

delta_f = f2 - f1;

dB = 50;

N = dB*Fs/(22*delta_f)
#N = 40

red_dc = medfilt1(red, Fs*2);
#redac = filter (b, a, red);
red_ac = red - red_dc;

ppgf = fir1( 40 , [ f1/Fn , f2/Fn], "pass");
#red_filtered = filter(ppgf,1,red_ac);
red_filtered = filtfilt(ppgf,1,red_ac);

dlmwrite (dataout, horzcat(time, red_ac ,red_filtered), " " )

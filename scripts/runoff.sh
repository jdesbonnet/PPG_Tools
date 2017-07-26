gunzip -c ../data/20170723/overnight.ppg.gz | java FilterChannel 0 |  tail -n +20000 | java Clean | cut -d ' ' -f 1,6,7 | ./fft 4096 > t.matrix
gnuplot plot_waterfall.gnuplot



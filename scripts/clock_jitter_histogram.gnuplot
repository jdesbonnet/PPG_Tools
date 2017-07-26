binwidth=0.0001
bin(x,width)=width*floor(x/width)
set xrange [0:0.020]
plot 'jitter.dat' using (bin($1,binwidth)):(1.0) smooth freq with boxes
pause -1


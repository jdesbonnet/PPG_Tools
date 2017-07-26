binwidth=0.001
bin(x,width)=width*floor(x/width)
plot 'jitter.dat' using (bin($1,binwidth)):(1.0) smooth freq with boxes


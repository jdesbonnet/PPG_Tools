/** 
 * FFT waterfall plot. Pass size of FFT as parameter eg ./fft 256.
 * Data is read from stdin and written to stdout.
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <math.h>
#include <fftw3.h>

void main (int argc, char **argv) {

	int i,j=0;

	// Number of samples in FFT slice
	int n = atoi(argv[1]);

	// Samples per second
	int sps = atoi(argv[2]);

	double t;

	// Got N real values. Result of FFT is
	// N/2+1 complex values due to hermitian redundancy.
	double in_red[n],in_nir[n];
	double mag_red,mag_nir;
	double f;
	fftw_complex out_red[n/2+1], out_nir[n/2+1];
	fftw_plan plan_red, plan_nir;

	// Real to complex, 1D FFT.
	plan_red = fftw_plan_dft_r2c_1d(n, in_red, out_red, FFTW_ESTIMATE);
	plan_nir = fftw_plan_dft_r2c_1d(n, in_nir, out_nir, FFTW_ESTIMATE);

	while (!feof(stdin)) {

		for (i = 0; i < n; i++) {
			if (feof(stdin)) {
				break;
			}
			fscanf (stdin,"%lf %lf %lf", &t, &in_red[i], &in_nir[i]);
		}

		fftw_execute(plan_red);
		fftw_execute(plan_nir);
		for (i = 0; i < (n/2)+1; i++) {
			mag_red = sqrt(out_red[i][0]*out_red[i][0] + out_red[i][1]*out_red[i][1]);
			mag_nir = sqrt(out_nir[i][0]*out_nir[i][0] + out_nir[i][1]*out_nir[i][1]);
			f = (double)(i*sps)/(double)n;
			t = (double)(j*n)/(double)sps;
			fprintf (stdout,"%f %f %f %f\n",f,t,mag_red,mag_nir);
		}
		j++;
	}
}

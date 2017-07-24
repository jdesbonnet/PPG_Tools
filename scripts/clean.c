/** 
 * Remove corrupted records from data file.
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <math.h>
#include <fftw3.h>

void main (int argc, char **argv) {

	double t, red, nir;
	double last_red, last_nir;
	int line = 0;

 	fscanf (stdin,"%lf %lf %lf", &t, &last_red, &last_nir);

	while (!feof(stdin)) {

		line++;

		if (feof(stdin)) {
			return;
		}
		fscanf (stdin,"%lf %lf %lf", &t, &red, &nir);

		if ( fabs(red - last_red) > 1000) {
			fprintf(stderr,"dropping line %d\n", line);
			continue;
		}
		if ( fabs(nir - last_nir) > 1000) {
			fprintf(stderr,"dropping line %d\n", line);
			continue;
		}
		fprintf (stdout, "%f %f %f\n", t, red, nir);
		last_red = red;
		last_nir = nir;
	}
}

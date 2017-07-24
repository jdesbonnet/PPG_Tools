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
	int drop = 0;
	int successive_drop_count = 0;

 	fscanf (stdin,"%lf %lf %lf", &t, &last_red, &last_nir);

	while (!feof(stdin)) {

		line ++;

		fscanf (stdin,"%lf %lf %lf", &t, &red, &nir);

		drop = 0;

		if ( red > (double)(1<<24) ) {
			fprintf (stderr,"*** %f\n", red);
			drop = 1;
		}
		if ( nir > (double)(1<<24) ) {
			drop = 1;
		}
		if ( fabs(red - last_red) > 1000) {
			drop = 1;
		}
		if ( fabs(nir - last_nir) > 1000) {
			drop = 1;
		}

		if (drop == 1) {
			successive_drop_count++;
		}

		if (successive_drop_count == 0) {
			fprintf (stdout, "%f %f %f\n", t, red, nir);
		}

		if (successive_drop_count == 0 && successive_drop_count > 2) {
			last_red = red;
			last_nir = nir;
		}
	}
}

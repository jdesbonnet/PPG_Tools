import java.io.*;

/**
 * Find start of pulses.
 */
public class Pulse {
	public static void main (String[] arg) throws Exception {
		String line;
		double time;
		double last_dred_cross_time=0;
		double systole_start_time=0;
		double systole_peak_time;
		double red, nir, dred, dnir, last_dred=0, last_dnir=0;
		double crossing_red=0, crossing_nir=0;
		double systole_peak_red=0, systole_peak_nir=0;

		int state=0;

		double threshold = -5e-6;

		for (int i = 0; i < arg.length; i++) {
			if ("-x".equals(arg[i])) {
				threshold = Double.parseDouble(arg[i+1]);
				i++;
			}
		}

		System.err.println ("threshold=" + threshold);

		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {

			String[] p = line.split(" ");

			time = Double.parseDouble(p[0]);
			red = Double.parseDouble(p[1]);
			nir = Double.parseDouble(p[2]);
			dred = Double.parseDouble(p[3]);
			dnir = Double.parseDouble(p[4]);

			// Systole start is measured by a sudden negative pulse. This records
			// the turning point when it start to shoots down. There are a few
			// times when the first derivitive of PPG is negative so this is 
			// not a sufficient condition to trigger start of system. 
			if (dred < 0 && last_dred >= 0) {
				last_dred_cross_time = time;
				crossing_red = red;
				crossing_nir = nir;	
			}



			// Looking for a point where there is a strong negative edge.
			// Previous block of code recoreded the start of this edge.
			if (state==0 && (dred < threshold)) {
				// Systole started
				// Output last cycle
				System.out.println (systole_start_time + " "  + (crossing_red - systole_peak_red) + " " + (last_dred_cross_time - systole_start_time));
				systole_start_time = last_dred_cross_time;
				state = 1;
			}

			// Start of systole detected, looking for peak
			if (state == 1) {
				if (dred > 0) {
					// This is the systole peak
					// TODO extrapolate 
					systole_peak_time = time;
					systole_peak_red = red;
					systole_peak_nir = nir;
					state = 2;
				}
			}

			// Looking for end of cycle where next systole begins
			if ( (state == 2) && (time - systole_start_time > 0.4) ) {
				state = 0;
			}

			last_dred = dred;

		}
	}
}

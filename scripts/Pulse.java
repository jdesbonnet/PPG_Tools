import java.io.*;

/**
 * Find start of pulses.
 *
 * Elements of PPG waveform:
 *
 *           P 
 *           /\
 *          /  \__T
 *         /      \
 *        /        \__D
 *       /         C  \
 *      /              \
 *     /                \
 *   _/                  \_
 *    S                    S
 *
 * S: start point
 * P: percussion wave
 * T: tidal wave (reflection from small artery).
 * C: incisura wave (end-point of systolic phase when aortic valve is closed).
 * D: Dicrotic wave
 *
 * Pulse width (period) is currently defined as S-to-S. P-to-P might be
 * more accurate however (?).
 */
public class Pulse {
	public static void main (String[] arg) throws Exception {
		String line;
		double time, last_time=0;
		double last_dred_cross_time=0;
		double systole_start_time=0;
		double systole_peak_time;
		double red, nir;
		//double last_red=0, last_nir=0;

		// First time derivative
		double dred, dnir, last_dred=0, last_dnir=0;

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

		// Header
		System.out.println ("# pulse-start-time red-amplitude nir-amplitude");

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
				// Output last cycle (start of cycle is currently defined as start of systole (S).

				double pulse_period = last_dred_cross_time - systole_start_time;

				if (pulse_period > 0.5 && pulse_period < 1.2) {
					System.out.println (systole_start_time 
					+ " " + (crossing_red - systole_peak_red) 
					+ " " + (crossing_nir - systole_peak_nir)
					+ " " + pulse_period);
				}
	
				systole_start_time = last_dred_cross_time;
				state = 1;
			}

			// Start of systole detected, looking for peak (when dred transitions from - to +)
			if (state == 1) {
				if (dred > 0) {
					// This is the systole peak
					// TODO extrapolate 

					double dy = dred-last_dred;
					double dx = time-last_time;
					double tc = -last_dred * dx / dy;

					systole_peak_time = time + tc;
					systole_peak_red = red;
					systole_peak_nir = nir;

					// Output last cycle
					//System.out.println (last_systol_peak_time + " "

					state = 2;
				}
			}

			// Looking for end of cycle where next systole begins
			if ( (state == 2) && (time - systole_start_time > 0.4) ) {
				state = 0;
			}

			last_dred = dred;
			last_time = time;
			//last_red = red;
			//last_nir = nir;
		}
	}
}

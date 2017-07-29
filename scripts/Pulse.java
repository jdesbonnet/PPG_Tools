import java.io.*;

/**
 * Find start of pulses.
 */
public class Pulse {
	public static void main (String[] arg) throws Exception {
		String line;
		double time;
		double pulseStartTime=0;
		double red, dred, nir, dnir;
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

			if (state==0 && (dred < threshold)) {
				System.out.println (time);
				pulseStartTime = time;
				state = 1;
			}
			if ( (state == 1) && (time-pulseStartTime > 0.3) ) {
				state = 0;
			}

			//System.out.println (time + " " + red + " " + nir + " " + state );

		}
	}
}

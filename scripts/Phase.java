import java.io.*;

/**
 * Calculate signal phase between two PPG sensors.
 */
public class Phase {
	public static void main (String[] arg) throws Exception {
		String line_a,line_b;
		double time_a=0,time_b=0;
		double phase;

		LineNumberReader ra = new LineNumberReader(new FileReader(arg[0]));
		LineNumberReader rb = new LineNumberReader(new FileReader(arg[1]));

		while ( (line_a = ra.readLine()) != null) {


			if (line_a.startsWith("#")) {
				continue;
			}

			String[] pa = line_a.split(" ");

			time_a = Double.parseDouble(pa[0]);

			while (time_b < time_a) {
				line_b = rb.readLine();
				if (line_b==null) {
					return;
				}
				if (line_b.startsWith("#")) {
					continue;
				}
				String[] pb = line_b.split(" ");
				time_b = Double.parseDouble(pb[0]);
			}

			phase = time_b - time_a;

			if (phase < 0.4) {
				System.out.println (time_a + " " + phase );
			}

		}
	}
}

import java.io.*;

/**
 * Calculate phase between two sensors.
 */
public class Phase {
	public static void main (String[] arg) throws Exception {
		String line_a,line_b;
		double time_a=0,time_b=0;
		double phase;

		LineNumberReader ra = new LineNumberReader(new FileReader(arg[0]));
		LineNumberReader rb = new LineNumberReader(new FileReader(arg[1]));

		while ( (line_a = ra.readLine()) != null) {


			time_a = Double.parseDouble(line_a);

			while (time_b < time_a) {
				line_b = rb.readLine();
				time_b = Double.parseDouble(line_b);
			}

			phase = time_b - time_a;

			System.out.println (time_a + " " + phase );

		}
	}
}

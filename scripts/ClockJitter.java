import java.io.*;

/**
 * Extract sample jitter (as measured on host computer receiving record via UART).
 */
public class ClockJitter {
	public static void main (String[] arg) throws Exception {
		String line;
		double time;
		double lastTime = 0;

		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");

			try {
				time = Double.parseDouble(p[0]);
			} catch (NumberFormatException e) {
				continue;
			}

			System.out.println (
				(time-lastTime)
			);
			lastTime = time;
		}
	}
}

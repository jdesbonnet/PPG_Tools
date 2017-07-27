import java.io.*;

/**
 * Remove the DC component of PPG. Expecting format:
 * host-clock hardware-clock device red nir 
 */
public class HighPassFilter {
	public static void main (String[] arg) throws Exception {
		String line;
		long red,nir;
		long lastRed = 0, lastNir=0;
		long lpfRed1=0, lpfNir1=0;
		long lpfRed2=0, lpfNir2=0;

		// Low pass filter intended to center each heart beat on zero
		// so that zero crossing detector used to detect start-of-beat.
		// TODO: have my doubts about the effectiveness of this method.
		final int filterTC1 = 8;

		// Low pass filter intended to detect long term changes to DC
		// level due to change in position of sensor etc.
		final int filterTC2 = 256;

		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");

			red = Long.parseLong(p[3]);
			nir = Long.parseLong(p[4]);

			lpfRed1 = (red + (filterTC1-1)*lpfRed1)/filterTC1;
			lpfNir1 = (nir + (filterTC1-1)*lpfNir1)/filterTC1;

			lpfRed2 = (red + (filterTC2-1)*lpfRed2)/filterTC2;
			lpfNir2 = (nir + (filterTC2-1)*lpfNir2)/filterTC2;

			System.out.println (
			p[0] + " " + p[1] + " " + p[2] 
			+ " " + red + " " + nir
			+ " " + (red-lpfRed1) + " " + (nir - lpfNir1)
			+ " " + (red-lpfRed2) + " " + (nir - lpfNir2)
			+ " " + lpfRed2 + " " + lpfNir2
			+ " " + (red-lastRed) + " " + (nir - lastNir)
			);

			lastRed = red;
			lastNir = nir;
		}
	}
}

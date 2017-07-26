import java.io.*;

/**
 * Remove the DC component of PPG.
 */
public class HighPassFilter {
	public static void main (String[] arg) throws Exception {
		String line;
		long red,nir;
		long lpfRed=0, lpfNir=0;

		final int filterTC = 256;

		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");

			red = Long.parseLong(p[3]);
			nir = Long.parseLong(p[4]);

			lpfRed = (red + (filterTC-1)*lpfRed)/filterTC;
			lpfNir = (nir + (filterTC-1)*lpfNir)/filterTC;

			System.out.println (
			p[0] + " " + p[1] + " " + p[2] 
			+ " " + red + " " + nir
			+ " " + (red-lpfRed) + " " + (nir-lpfNir)
			+ " " + lpfRed + " " + lpfNir
			);
		}
	}
}

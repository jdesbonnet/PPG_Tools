import java.io.*;

/**
 * Remove records corrupted I2C bus errors etc. Assuming that
 * Level1 clean has already been perfomed on data and has been
 * processed by HighPass filter (providing raw, ac and dc 
 * components of the signal).
 */
public class CleanLevel2 {
	public static void main (String[] arg) throws Exception {
		String line;
		int sensor;
		int red,nir;
		int red_lpf=0;
		double time;
		int hwclock;
		int dropCount=0;
		int totalRecords=0;

		int filterTC = 128;

		int threshold = 5000;

		for (int i = 0; i < arg.length; i++) {
			if ("-x".equals(arg[i])) {
				threshold = Integer.parseInt(arg[i+1]);
				i++;
			}
		}

		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {

			totalRecords++;

			String[] p = line.split(" ");

			// Remaining lines are formatted as <host-time-tag> $PPGV0 device red nir hwclock checksum
			if (p.length != 5) {
				System.err.println ("line " + r.getLineNumber() + ": wrong number of columns " + p.length + " line: " + line);
				continue;
			}

			time = Double.parseDouble(p[0]);
			hwclock = Integer.parseInt(p[1]);
			sensor = Integer.parseInt(p[2]);
			red = Integer.parseInt(p[3]);
			nir = Integer.parseInt(p[4]);

			red_lpf = (red + (filterTC-1)*red_lpf)/filterTC;

			if ( Math.abs(red-red_lpf) > threshold) {
				System.err.println ("line " + r.getLineNumber() + ": ac signal too high (" + (red-red_lpf) +"): dropping record");
				dropCount++;
				continue;
			}

			System.out.println(line);
		}
		System.err.println ("dropped " + dropCount + " of " + totalRecords);
	}
}

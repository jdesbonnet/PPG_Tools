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
		int red,nir,redac1,nirac1,redac2,nirac2,reddc,nirdc;
		double time;
		int hwclock;
		int dropCount=0;
		int totalRecords=0;

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
			if (p.length != 13) {
				System.err.println ("line " + r.getLineNumber() + ": wrong number of columns " + p.length + " line: " + line);
				continue;
			}

			time = Double.parseDouble(p[0]);
			hwclock = Integer.parseInt(p[1]);
			sensor = Integer.parseInt(p[2]);
			red = Integer.parseInt(p[3]);
			nir = Integer.parseInt(p[4]);
			redac1 = Integer.parseInt(p[5]);
			nirac1 = Integer.parseInt(p[6]);
			redac2 = Integer.parseInt(p[7]);
			redac2 = Integer.parseInt(p[8]);
			reddc = Integer.parseInt(p[9]);
			nirdc = Integer.parseInt(p[10]);

			if ( Math.abs(redac2) > threshold) {
				System.err.println ("line " + r.getLineNumber() + ": ac signal too high (" + redac2 +"): dropping record");
				dropCount++;
				continue;
			}

			System.out.println(line);
		}
		System.err.println ("dropped " + dropCount + " of " + totalRecords);
	}
}

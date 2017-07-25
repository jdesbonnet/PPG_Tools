import java.io.*;

public class Resample {
	public static void main (String[] arg) throws Exception {
		String line;
		int red,nir;
		int lastRed=0, lastNir=0;
		int sumRed=0, sumNir=0;
		int sumCount=0;
		double time;
		double sampleRate = 100;
		double samplePeriod = 1/sampleRate;
		long i;
		long timeBin;
		long lastTimeBin=0;

		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");
			if (p.length != 7) {
				continue;
			}

			try {
				time = Double.parseDouble(p[0]);
				red = Integer.parseInt(p[3]);
				nir = Integer.parseInt(p[4]);
			} catch (NumberFormatException e) {
				System.err.println("parse error on line " + r.getLineNumber());
				continue;
			}

			timeBin  = (long)(time / samplePeriod);

			if (lastRed==0) {
				lastRed = red;
				lastNir = nir;
				lastTimeBin = timeBin;
			}

			sumRed += red;
			sumNir += nir;
			sumCount++;

			if (timeBin == lastTimeBin) {
				continue;
			}


			if (timeBin > lastTimeBin+1) {
				System.err.println ("missing sample at line " + r.getLineNumber());
				for (i = lastTimeBin; i < timeBin; i++) {
					System.out.println ( String.format("%f",i*samplePeriod) + " " + lastRed + " " + lastNir);
				}
			}
	
			sumRed /= sumCount;
			sumNir /= sumCount;

			System.out.println ( String.format("%f",timeBin*samplePeriod) + " " + sumRed + " " + sumNir);

			lastRed = sumRed;
			lastNir = sumNir;
			sumRed=0;
			sumNir=0;
			sumCount=0;
			lastTimeBin = timeBin;

		}
	}
}

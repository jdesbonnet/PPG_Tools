import java.io.*;

public class Clean {
	public static void main (String[] arg) throws Exception {
		String line;
		long red,nir;
		long lastRed=0, lastNir=0;
		int skipCount=0;

		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");

			if (p.length != 7) {
				System.err.println ("line " + r.getLineNumber() + ": unexpected format");
				continue;
			}

			red = Long.parseLong(p[3]);
			nir = Long.parseLong(p[4]);
			if (red > (1<<22)) {
				System.err.println ("line " + r.getLineNumber() + ": red too large (" + red + ")");
				continue;
			}
			if (nir > (1<<22)) {
				System.err.println ("line " + r.getLineNumber() + ": NIR too large (" + nir + ")");
				continue;
			}
			if (red == 0) {
				continue;
			}
			if (nir == 0) {
				continue;
			}

			if (lastRed == 0) {
				lastRed = red;
				lastNir = nir;
			}

			if (skipCount < 8 && Math.abs(red-lastRed) > 4000) {
				System.err.println ("line " + r.getLineNumber() + ": red transient (current=" + red + ", last=" + lastRed + ")");
				skipCount++;
				continue;
			}

			System.out.println(line);

			lastRed = red;
			lastNir = nir;
			skipCount = 0;
		}
	}
}

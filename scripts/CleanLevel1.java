import java.io.*;

/**
 * Remove records corrupted by transmission over UART.
 */
public class CleanLevel1 {
	public static void main (String[] arg) throws Exception {
		String line;
		long red,nir;

		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");

			if (p[1].startsWith("#")) {
				System.out.println(line);
				continue;
			}

			if (p.length != 7) {
				System.err.println ("line " + r.getLineNumber() + ": wrong number of columns " + p.length);
				continue;
			}
			if ( ! p[1].startsWith("$PPG")  ) {
				System.err.println ("line " + r.getLineNumber() + ": does not have $PPG header");
				continue;
			}

			System.out.println(line);
		}
	}
}

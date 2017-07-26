import java.io.*;

/**
 * Remove records corrupted by transmission over UART.
 */
public class CleanLevel1 {
	public static void main (String[] arg) throws Exception {
		String line;
		int red,nir,chk,hwt;

		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {

			if (line.length() == 0) {
				continue;
			}

			String[] p = line.split(" ");

			if (p.length < 2) {
				continue;
			}

			// All lines starting with # pass through
			if (p[1].startsWith("#")) {
				System.out.println(line);
				continue;
			}


			// Remaining lines are formatted as <host-time-tag> $PPGV0 device red nir hwclock checksum
			if (p.length != 7) {
				System.err.println ("line " + r.getLineNumber() + ": wrong number of columns " + p.length + " line: " + line);
				continue;
			}
			if ( ! p[1].startsWith("$PPG")  ) {
				System.err.println ("line " + r.getLineNumber() + ": does not have $PPG header");
				continue;
			}

			try {
				//time = Double.parseDouble(p[0]);
				red = Integer.parseInt(p[3],16);
				nir = Integer.parseInt(p[4],16);
				hwt = Integer.parseInt(p[5],16);
				chk = Integer.parseInt(p[6].substring(1),16);
                        } catch (NumberFormatException e) {
                                System.err.println("line " + r.getLineNumber() + ": number parse exception " + e.getMessage());
                                continue;
                        }

			if ( ((red+nir)&0xff) != chk) {
				System.err.println("line " + r.getLineNumber() + ": checksum error ");
				continue;
			}

			System.out.println(line);
		}
	}
}

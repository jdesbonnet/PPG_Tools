import java.io.*;

public class DeHex {
	public static void main (String[] arg) throws Exception {
		String line;
		int red, nir, sum;
		int chk,hwt;

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");
			if (p.length != 7) {
				System.err.println("bad length");
				continue;
			}
			if (!"$PPGV0".equals(p[1]))  {
				System.err.println("bad format");
				continue;
			}

			try {
			red = Integer.parseInt(p[3],16);
			nir = Integer.parseInt(p[4],16);
			hwt = Integer.parseInt(p[5],16);
			chk = Integer.parseInt(p[6].substring(1),16);
			} catch (NumberFormatException e) {
				System.err.println("parse error");
				continue;
			}

			sum = (red + nir) & 0xff;

			if (chk != sum) {
				System.err.println ("bad checksum");
				continue;
			}


			System.out.print(p[0]+" ");
			System.out.print(hwt + " ");
			System.out.print(p[2]+" ");
			System.out.print(red + " ");
			System.out.print(nir);
			System.out.println("");
			
		}
	}
}
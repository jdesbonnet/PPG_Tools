import java.io.*;

public class ZeroTime {
	public static void main (String[] arg) throws Exception {
		int i;
		String line;


		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));

		double t, tzero=0;

		for (i = 0; i < arg.length; i++) {
			if ("-t".equals(arg[i])) {
				tzero = Double.parseDouble(arg[i+1]);	
				i++;
			}
		}


		if (tzero == 0) {
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");
			if (p[1].startsWith("#")) {
				continue;
			}
			tzero = Double.parseDouble(p[0]);
			break;
		}
		}


		System.err.println ("tzero=" + tzero);


		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");
			t = Double.parseDouble(p[0]);
			System.out.print (t - tzero);
			for (i = 1; i < p.length; i++) {
				System.out.print (" " + p[i]);
			}
			System.out.println("");
		}
	}
}

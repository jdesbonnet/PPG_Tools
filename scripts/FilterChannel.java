import java.io.*;

public class FilterChannel {
	public static void main (String[] arg) throws Exception {
		String line;

		String selectedSensor = arg[0];

		LineNumberReader r = new LineNumberReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");
			//if (p.length != 7) {
			//	continue;
			//}
			if (p[1].startsWith("#")) {
				System.out.println(line);
				continue;
			}

			if (p.length != 7) {
				System.err.println ("line " + r.getLineNumber() + " unexpected number of columns: " + line);
				continue;
			}

			if (p[2].equals(selectedSensor)) {
				System.out.println(line);
			}
		}
	}
}

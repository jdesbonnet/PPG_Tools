import java.io.*;

public class FilterChannel {
	public static void main (String[] arg) throws Exception {
		String line;

		String selectedSensor = arg[0];

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");
			if (p.length != 7) {
				continue;
			}
			if (p[2].equals(selectedSensor)) {
				System.out.println(line);
			}
		}
	}
}

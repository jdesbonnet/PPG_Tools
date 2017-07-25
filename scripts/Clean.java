import java.io.*;

public class Clean {
	public static void main (String[] arg) throws Exception {
		String line;
		long red,nir;

		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		while ( (line = r.readLine()) != null) {
			String[] p = line.split(" ");
			if (p.length != 7) {
				continue;
			}
			red = Long.parseLong(p[3]);
			nir = Long.parseLong(p[4]);
			if (red > (1<<24)) {
				continue;
			}
			if (nir > (1<<24)) {
				continue;
			}

			System.out.println(line);
		}
	}
}

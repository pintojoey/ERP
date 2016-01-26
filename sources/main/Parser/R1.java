package Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class R1 {

  public static void main(String[] args) {

	R1 obj = new R1();
	obj.run();

  }

  public void run() {

	String csvFile = "c1.csv";
	BufferedReader br = null;
	String line = "";
	String csvSplitBy = ",";

	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {

		        // use comma as separator
			String[] country = line.split(csvSplitBy);

			System.out.println("Country [code= " + country[4] 
                                 + " , name=" + country[5] + "]");

		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("Done");
  }

}
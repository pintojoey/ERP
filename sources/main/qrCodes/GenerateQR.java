/**
 * 
 */
package qrCodes;

/**
 * @author Joey
 *
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
 
public class GenerateQR {
	public static void main(String[] args) {
		generateQR("This is test data 12398+6455", "", "QR.png");
	}
public static void generateQR(String message,String file_path,String file_name) {
 
ByteArrayOutputStream out = QRCode.from(message).to(ImageType.PNG).stream();
 
try {
	File fileSaveDir = new File(file_path);
    if (!fileSaveDir.exists()) {
        fileSaveDir.mkdirs();
    }
FileOutputStream fout = new FileOutputStream(new File(file_path+file_name));
 
fout.write(out.toByteArray());
 
fout.flush();
fout.close();
 
} catch (FileNotFoundException e) {
// Do Logging
e.printStackTrace();
} catch (IOException e) {
// Do Logging
e.printStackTrace();
}
}
}

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
    public static void logError(String message) {
        try {
            FileWriter writer = new FileWriter("log.err", true);
            writer.write(getCurrentTimeStamp() + " - " + message + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }


}

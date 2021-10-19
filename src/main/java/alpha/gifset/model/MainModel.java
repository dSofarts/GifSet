package alpha.gifset.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainModel {
    public static String getUrlContent(String urlAddress) {
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content.toString();
    }
    public static String getDateYesterday() {
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date.getTime());
    }
    public static String gifUrl(String gifUrl, String gifApi, String tag) {
        String staticPartOfCode1 = "/random?api_key=";
        String staticPartOfCode2 = "&tag=";
        String urlAddress = gifUrl + staticPartOfCode1 + gifApi + staticPartOfCode2 + tag;
        return urlAddress;
    }
}

package alpha.gifset.model;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GifModel {

    public static String gifAddUrl(String tag, String gifUrl, String gifApi) {

        String urlAddress = gifUrl + "/random?api_key=" + gifApi + "&tag=" + tag;
        StringBuffer contentGif = new StringBuffer();
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                contentGif.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return contentGif.toString();
    }
}

package sample.apigoogleJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
//reference by https://stackoverflow.com/questions/8147284/how-to-use-google-translate-api-in-my-java-application/16325094
//tham khảo dựa trên stackoverflow.com
// json javascript contains key->value form object usage mediate data
public class ReadFileJSONApi {
    public static String translate(String langFrom, String langTo, String text) throws IOException {
        // my script url
        String urlOfJson = "https://script.google.com/macros/s/AKfycbw2uext3JqJVmEo4Iv9aMNetrKfjRYTZEhsLUcr5nb-i7SZXew/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlOfJson);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}

package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadJobAPI {

    //Github API
    //Sample JsonParser from: https://github.ugrad.cs.ubc.ca/CPSC210-2018W-T1/JsonParserExample
    public Map<String, String> retreveData () throws MalformedURLException, IOException, JSONException {
        BufferedReader br = null;

        try {

            URL url = new URL("https://jobs.github.com/positions.json?description=software&location=california");
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            JSONArray jArray = new JSONArray(sb.toString());
            JSONObject jo = jArray.getJSONObject(randomInt());
            return storeData(jo);

        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public String splitURL(JSONObject jo) throws JSONException {
        String howtoapply = jo.getString("how_to_apply");
        if (howtoapply.contains("<p>")) {
            String[] output = howtoapply.split("\">");
            String[] output2 = output[1].split("</a>");
            return output2[0];
        }
        return "";
    }

    private Map<String, String> storeData(JSONObject jo) throws JSONException{
        Map jobReco = new HashMap();
        jobReco.put("type",jo.getString("type"));
        jobReco.put("title",jo.getString("title"));
        jobReco.put("company",jo.getString("company"));
        jobReco.put("location",jo.getString("location"));
        jobReco.put("url",splitURL(jo));
        return jobReco;
    }

    private int randomInt(){
        Random random = new Random();
        int randValue = random.nextInt(5);
        return randValue;
    }
}

package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadJobAPI {

    //Github API
    //Sample JsonParser from: https://github.ugrad.cs.ubc.ca/CPSC210-2018W-T1/JsonParserExample
    public Map<String, String> retreveData () {
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

            if (br != null) {
                br.close();
            }
            return storeData(jo);

        } catch (Exception e){
            System.out.println("retrieve data error");
        }

        // return empty map
        return new HashMap<>();

    }

    public String splitURL(JSONObject jo) throws JSONException {
        String howtoapply = jo.getString("how_to_apply");
        if (howtoapply.contains("<p>")) {
            String[] output = howtoapply.split("<p>");
            String[] output2 = output[1].split("</p>");
            System.out.println(output2);
            return output2[0];

        }
        return "";
    }

    private Map<String, String> storeData(JSONObject jo) {
        Map jobReco = new HashMap();
        try {
            jobReco.put("type",jo.getString("type"));
            jobReco.put("title",jo.getString("title"));
            jobReco.put("company",jo.getString("company"));
            jobReco.put("location",jo.getString("location"));
            jobReco.put("url",this.splitURL(jo));
        } catch (Exception e) {
            // stub
            System.out.println("store data error");
        }
        return jobReco;

    }

    //to get top 5 latest jobs
    private int randomInt(){
        Random random = new Random();
        int randValue = random.nextInt(5);
        return randValue;
    }
}

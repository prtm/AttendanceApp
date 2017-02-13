package parser;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public static String parseFeed(String content) {
        try {
            String token = null, name = null;
            JSONObject object = new JSONObject(content);

            boolean status = object.getBoolean("status");
            if (status) {
                name = object.getJSONObject("info").getString("name");
                token = object.getString("token");
                return name + "," + token;
            }


            return token;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jecrc.prtm.attendanceapp.DownloadStudent;

public class JsonParserStudent {

    public static List<DownloadStudent> parseFeed(String content) {
        try {
            List<DownloadStudent> list = new ArrayList<>();
            JSONObject object = new JSONObject(content);
            JSONArray array = object.getJSONArray("data");
            DownloadStudent downloadSubject;
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1 = array.getJSONObject(i);
                downloadSubject = new DownloadStudent();
                downloadSubject.setRollno(object1.getString("rollno"));
                downloadSubject.setName(object1.getString("name"));
                downloadSubject.setGender(object1.getString("gender"));
                list.add(downloadSubject);
            }

            return list;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
}

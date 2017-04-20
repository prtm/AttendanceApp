package jecrc.prtm.attendanceapp.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import jecrc.prtm.attendanceapp.models.DownloadClass;

public class SubjectDParser {

    public static List<DownloadClass> parseFeed(String content) {
        try {
            List<DownloadClass> list = new ArrayList<>();
            JSONObject object = new JSONObject(content);
            JSONArray array = object.getJSONArray("data");
            DownloadClass downloadClass;
            for (int i = 0; i < array.length(); i++) {
                JSONObject object1 = array.getJSONObject(i);
                downloadClass = new DownloadClass();
                downloadClass.setClassId(object1.getString("id"));
                downloadClass.setName(object1.getString("name"));
                list.add(downloadClass);
            }

            return list;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
}

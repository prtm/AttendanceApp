package jecrc.prtm.attendanceapp.download_upload;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import jecrc.prtm.attendanceapp.L;


public class DownloadService extends IntentService {
    private static final String TAG = "DownloadService";
    public static String url = "http://192.168.1.100/tutorial_download.php";

    public DownloadService() {
        super(DownloadService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Service Started!");
        try {

            requestVolley(url);

        } catch (Exception e) {
            L.tmlong(getApplicationContext(), e.toString());
        }

        Log.d(TAG, "Service Stopping!");
        this.stopSelf();
    }

    private void requestVolley(String url) {
        StringRequest request = new StringRequest(url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

//                        List<Download_Information> downloadInformation;
//                        downloadInformation = JsonParser.parseFeed(response);
//                        try {
//                            if (downloadInformation != null) {
//                                for (int i = 0; i < downloadInformation.size(); i++) {
//
////                                    L.tmlong(getApplicationContext(), downloadInformation.get(i)
////                                            .getAge()
////                                            + " and "
////                                            + downloadInformation.get(i).getName());
//
//                                }
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        L.tmlong(getApplicationContext(), response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                L.tmlong(getApplicationContext(), error.toString());
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}

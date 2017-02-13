package jecrc.prtm.attendanceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import adapters.Class_adapter;
import interfaces.Class_info;
import parser.JsonParser_Sub;

public class ClassView extends AppCompatActivity implements Class_info {
    private static final String url = "http://192.168.1.100/attend/api/list.php?type=subjects&class=";
    public static List<DownloadClass> downloadClasses = new ArrayList<>();
    private boolean success = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_view);
        RecyclerView attendance_recycler = (RecyclerView) findViewById(R.id.attendance_recycler);
        Class_adapter attendanceAdapter = new Class_adapter(this, downloadClasses);
        attendance_recycler.setAdapter(attendanceAdapter);
        attendance_recycler.setLayoutManager(new GridLayoutManager(this, 2));

    }

    @Override
    public void onClicked(String s) {

        requestVolley(s);
    }

    private void requestVolley(final String x) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = sharedPreferences.getString("Token", "");
        StringRequest request = new StringRequest(Request.Method.GET, url + x + "&token=" + token,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("{\"error\":\"Sorry, you are not allowed to view this list\"}")) {
                            Intent intent = new Intent(ClassView.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            SubjectView.downloadSubjects = JsonParser_Sub.parseFeed(response);
                            if (SubjectView.downloadSubjects == null) {
                                L.lm("null error");
                                success = false;
                            } else {
                                success = true;
                            }
                        }
                        nextScreen(x);
                        L.lm(response);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                L.tmlong(getApplicationContext(), error.toString());
                success = false;
                nextScreen(x);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void nextScreen(String s) {
        if (success) {
            Intent intent = new Intent(ClassView.this, SubjectView.class);
            Bundle bundle = new Bundle();
            bundle.putString("classId", s);
            intent.putExtras(bundle);
            startActivity(intent);

        }
//        else {
//            Snackbar.make(view.getRootView(), "Network Error", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//        }
    }
}

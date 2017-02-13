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

import java.util.List;

import adapters.Sub_adapter;
import interfaces.Sub_Info;
import parser.JsonParser_Student;

public class SubjectView extends AppCompatActivity implements Sub_Info {
    private static final String url = "http://192.168.1.100/attend/api/list.php?type=students&class=";
    public static List<DownloadClass> downloadSubjects;
    private String classId;
    private boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_view);
        classId = getIntent().getExtras().getString("classId");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_subject);
        Sub_adapter adapter = new Sub_adapter(this, downloadSubjects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void itemClicked(String subId) {
        requestVolley(subId);
    }

    private void requestVolley(final String subId) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String token = sharedPreferences.getString("Token", "");
        StringRequest request = new StringRequest(Request.Method.GET, url + classId + "&token=" + token,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("{\"error\":\"Sorry, you are not allowed to view this list\"}")) {
                            Intent intent = new Intent(SubjectView.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            /* CommonFragment.listStudent = JsonParser_Student.parseFeed(response);
                            if (CommonFragment.listStudent == null) {
                                L.lm("null error");
                                success = false;
                            } else {
                                L.lm(CommonFragment.listStudent.size() + "");
                                Att_pager.length = CommonFragment.listStudent.size();
                                success = true;
                            } */
                            Att_start.listStudent = JsonParser_Student.parseFeed(response);
                            if (Att_start.listStudent == null) {
                                L.lm("null error");
                                success = false;
                            } else {
                                L.lm(Att_start.listStudent.size() + "");
                                success = true;
                            }
                        }
                        nextScreen(subId);
                        L.lm(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                success = false;
                nextScreen(subId);
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private void nextScreen(String subId) {
        if (success) {
            Intent intent = new Intent(SubjectView.this, Att_view_sel.class);
            Bundle bundle = new Bundle();
            bundle.putString("classSubId", classId + "," + subId);
            intent.putExtras(bundle);
            startActivity(intent);

        }
//        else {
//            Snackbar.make(view.getRootView(), "Network Error", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//        }
    }
}

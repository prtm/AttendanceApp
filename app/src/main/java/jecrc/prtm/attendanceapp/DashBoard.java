package jecrc.prtm.attendanceapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import parser.JsonParser_Class;

public class DashBoard extends AppCompatActivity {
    private static final String url = "http://192.168.1.100/attend/api/list.php?type=classes&token=";
    private FloatingActionButton fab;
    private boolean success = false;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressClass);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                startActivity(new Intent(DashBoard.this, Att_pager.class));
                progressBar.setVisibility(View.VISIBLE);
                requestVolley();


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void nextScreen() {
        progressBar.setVisibility(View.GONE);
        if (success) {
            startActivity(new Intent(DashBoard.this, ClassView.class));

        } else {
            Snackbar.make(fab.getRootView(), "Network Error", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }


    private void requestVolley() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String x = sharedPreferences.getString("Token", "");
        StringRequest request = new StringRequest(Request.Method.GET, url + x,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        if (response.equals("{\"error\":\"Sorry, you are not allowed to view this list\"}")) {
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(DashBoard.this);
                            sharedPreferences.edit().putBoolean("Active", false).apply();
                            Intent intent = new Intent(DashBoard.this, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            ClassView.downloadClasses = JsonParser_Class.parseFeed(response);
                            if (ClassView.downloadClasses == null) {
                                L.lm("null error");
                                success = false;
                            } else {
                                success = true;
                            }
                        }
                        nextScreen();
                        L.lm(response);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                L.lm(error.toString());
                success = false;
                nextScreen();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}

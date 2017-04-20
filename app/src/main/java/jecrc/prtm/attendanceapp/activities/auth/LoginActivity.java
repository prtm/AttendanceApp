package jecrc.prtm.attendanceapp.activities.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import jecrc.prtm.attendanceapp.L;
import jecrc.prtm.attendanceapp.R;
import jecrc.prtm.attendanceapp.activities.HomeActivity;
import jecrc.prtm.attendanceapp.api.Config;
import jecrc.prtm.attendanceapp.parser.LoginDParser;

public class LoginActivity extends AppCompatActivity {
    private boolean success = false;
    private Button button;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean("Active", false)) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        button = (Button) findViewById(R.id.button1);
        final EditText username = (EditText) findViewById(R.id.editText2);
        final EditText passworded = (EditText) findViewById(R.id.editText1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String password = passworded.getText().toString();
                requestVolley(email, password);

            }
        });
    }

    private void nextScreen() {
        if (success) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("Active", true);
            editor.apply();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Snackbar.make(button.getRootView(), "Connection Error", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).setActionTextColor(ContextCompat.getColor(LoginActivity.this, R.color.colorAccent)).
                    setActionTextColor(ContextCompat.getColor(LoginActivity.this, R.color.colorPrimary))
                    .show();
        }
    }

    private void requestVolley(final String username, final String password) {
        StringRequest request = new StringRequest(Request.Method.POST, Config.URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        String s[];
                        try {

                            s = LoginDParser.parseFeed(response).split(",");
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("Name", s[0]);
                            editor.putString("Token", s[1]);
                            editor.apply();
                            success = true;

                        } catch (NullPointerException e) {
                            L.tm(LoginActivity.this, response);
                            e.printStackTrace();
                            success = false;
                        }
                        nextScreen();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                L.tm(LoginActivity.this, error.toString());
                success = false;
                nextScreen();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("username", "HODCS");
                params.put("password", "123");
                params.put("type", "hod");
//				params.put("roll_id", roll_no);
//
                return params;

            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}

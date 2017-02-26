package jecrc.prtm.attendanceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void myClick(View view) {
        L.lm(FirebaseInstanceId.getInstance().getToken());
    }
}

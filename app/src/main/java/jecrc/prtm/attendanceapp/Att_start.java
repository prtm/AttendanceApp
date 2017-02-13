package jecrc.prtm.attendanceapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapters.Att_adapter;

public class Att_start extends AppCompatActivity {
    public static List<DownloadStudent> listStudent;
    private String classId="", subId="";
    boolean majority=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_taking);
        try {
            majority = getIntent().getExtras().getBoolean("majority");
            String classSubId[] = getIntent().getExtras().getString("classSubId").split(",");
            classId=classSubId[0];
            subId=classSubId[1];
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_attendance);
        Att_adapter attendanceAdapter = new Att_adapter(this, listStudent,majority);
        recyclerView.setAdapter(attendanceAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

    }
}

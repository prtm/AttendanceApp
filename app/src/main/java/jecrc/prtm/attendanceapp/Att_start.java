package jecrc.prtm.attendanceapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import adapters.Att_adapter;

public class Att_start extends AppCompatActivity {
    public static List<DownloadStudent> listStudent;
    private String classId="", subId="";
    boolean majority=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_att_taking);
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
        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L.tmlong(Att_start.this.getApplicationContext(),"Uploading attendance...");
            }
        });

    }
}

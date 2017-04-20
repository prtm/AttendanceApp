package jecrc.prtm.attendanceapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.amulyakhare.textdrawable.TextDrawable;

import jecrc.prtm.attendanceapp.R;

public class AttViewActivity extends AppCompatActivity {
    private String classSubId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_att_view_sel);
        try {
            classSubId = getIntent().getExtras().getString("classSubId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextDrawable drawable = TextDrawable.builder().beginConfig().width(144).height(144).textColor(Color.BLACK).fontSize(18).endConfig()
                .buildRound("PRESENT", Color.YELLOW);
        TextDrawable drawable2 = TextDrawable.builder().beginConfig().width(144).height(144).textColor(Color.WHITE).fontSize(18).endConfig()
                .buildRound("ABSENT", Color.BLACK);


        //if about half class present, go to class attendance pager
        findViewById(R.id.equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttViewActivity.this, AttendanceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("classSubId", classSubId);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    public void selClick(View view) {
        if (R.id.present == view.getId()) {
            att_start(true);
        } else {
            att_start(false);
        }
    }

    private void att_start(boolean type) {
        Intent intent = new Intent(AttViewActivity.this, AttendanceOptActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putBoolean("majority", type);
        bundle.putString("classSubId", classSubId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}

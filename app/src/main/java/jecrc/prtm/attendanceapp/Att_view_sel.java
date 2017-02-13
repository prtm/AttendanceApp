package jecrc.prtm.attendanceapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;

public class Att_view_sel extends AppCompatActivity {
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
        TextDrawable drawable = TextDrawable.builder().beginConfig().width(72).height(72).endConfig()
                .buildRound("PRESENT", Color.RED);
        TextDrawable drawable2 = TextDrawable.builder().beginConfig().width(72).height(72).endConfig()
                .buildRound("ABSENT", Color.GREEN);

        //Intialization

        ImageView presentImg = (ImageView) findViewById(R.id.present);
        ImageView absentImg = (ImageView) findViewById(R.id.absent);
        presentImg.setImageDrawable(drawable);
        absentImg.setImageDrawable(drawable2);
        //if about half class present, go to class attendance pager
        findViewById(R.id.equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Att_view_sel.this, Att_pager.class);
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
        Intent intent = new Intent(Att_view_sel.this, Att_start.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putBoolean("majority", type);
        bundle.putString("classSubId", classSubId);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}

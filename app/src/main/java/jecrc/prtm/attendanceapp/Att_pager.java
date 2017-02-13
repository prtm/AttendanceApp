package jecrc.prtm.attendanceapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import fragments.Att_view_Fragment;
import interfaces.Att_status_change;

import static java.lang.Thread.sleep;

public class Att_pager extends AppCompatActivity implements Att_status_change {
    public static int length = 0;
    private int position = 0;
    ViewPager vpPager;
    private boolean[] status;
    private FragmentStatePagerAdapter adapterViewPager;
    private ImageView Imgstatus;
    private String classId = "", subId = "";
    private TextView checkedStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_pager);
        try {
            length = Att_view_Fragment.listStudent.size();
            String classSubId[] = getIntent().getExtras().getString("classSubId").split(",");
            classId = classSubId[0];
            subId = classSubId[1];
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        Imgstatus = (ImageView) findViewById(R.id.status);
        checkedStatus = (TextView) findViewById(R.id.checkedStatus);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //upload attendance
                L.tm(Att_pager.this, "Uploading...");
            }
        });

        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        Imgstatus.setImageResource(R.drawable.cancel);
        checkedStatus.setText(getResources().getString(R.string.absent));
        vpPager.setAdapter(adapterViewPager);
        vpPager.setOffscreenPageLimit(2);
        status = new boolean[length];
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                if (position == length - 1) {
                    fab.setVisibility(View.VISIBLE);
                } else {
                    fab.setVisibility(View.INVISIBLE);
                }
                Att_pager.this.position = position;
                if (Att_pager.this.status[position]) {
                    L.lm("Present Called");
                    Imgstatus.setImageResource(R.drawable.check);
                    checkedStatus.setText(getResources().getString(R.string.present));


                } else if (!Att_pager.this.status[Att_pager.this.position]) {
                    L.lm("Absent Called");
                    Imgstatus.setImageResource(R.drawable.cancel);
                    checkedStatus.setText(getResources().getString(R.string.absent));
                } else {
                    L.lm("Nothing Happens");
                    Imgstatus.setImageResource(R.drawable.myvector);
                }
            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        builder1.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        position = 0;
                        status = null;
                        finish();

                    }
                });
        builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();

            }
        });
        AlertDialog dialog1 = builder1.create();
        dialog1.setTitle("Discard Changes");
        dialog1.setMessage("Do you  Want to go Back?");
        dialog1.setCancelable(false);
        dialog1.show();
    }

    @Override
    public void statusSet(boolean stat) {
        L.lm(position + "--" + stat);
        status[position] = stat;
        final Animation animleftOut = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        final Animation animationRightOut = AnimationUtils.loadAnimation(this, R.anim.translate_right);
        final Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);

        if (stat) {
            checkedStatus.setAnimation(animationRightOut);
        } else {
            checkedStatus.setAnimation(animleftOut);
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                checkedStatus.setAnimation(fadein);
            }
        }, 200);


        vpPager.setCurrentItem(position + 1, true);

    }


    private static class MyPagerAdapter extends FragmentStatePagerAdapter {

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return length;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            Att_view_Fragment fragment = new Att_view_Fragment();
            args.putInt(Att_view_Fragment.ARG_SECTION_NUMBER, position);
            fragment.setArguments(args);
            return fragment;
        }


    }

}

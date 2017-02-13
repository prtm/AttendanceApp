package jecrc.prtm.attendanceapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import fragments.CommonFragment;
import interfaces.Att_status_change;

public class Att_pager extends AppCompatActivity implements Att_status_change {
    public static int length;
    private int position = 0;
    ViewPager vpPager;
    private boolean[] status;
    private ImageView Imgstatus;
    private String classId, subId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_pager);
        String a[] = getIntent().getExtras().getString("classSubId").split(",");
        classId = a[0];
        subId = a[1];
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        Imgstatus = (ImageView) findViewById(R.id.status);

        FragmentStatePagerAdapter adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setOffscreenPageLimit(2);
        status = new boolean[length];
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {
                Att_pager.this.position = position;
                if (Att_pager.this.status[Att_pager.this.position]) {
                    L.lm("Present Called");
                    Imgstatus.setImageResource(R.drawable.check);

                } else if (!Att_pager.this.status[Att_pager.this.position]) {
                    L.lm("Absent Called");
                    Imgstatus.setImageResource(R.drawable.cancel);
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
        vpPager.setCurrentItem(position + 1, true);
    }

    private static class MyPagerAdapter extends FragmentStatePagerAdapter {
        private static int NUM_ITEMS;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            NUM_ITEMS = length;
            L.lm(NUM_ITEMS + " length ");
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            CommonFragment fragment = new CommonFragment();
            args.putInt(CommonFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);
            return fragment;
        }


    }

}

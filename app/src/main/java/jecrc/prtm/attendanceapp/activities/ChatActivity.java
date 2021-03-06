package jecrc.prtm.attendanceapp.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import jecrc.prtm.attendanceapp.R;
import jecrc.prtm.attendanceapp.models.Treceived;
import jecrc.prtm.attendanceapp.models.Tsent;
import jecrc.prtm.attendanceapp.adapters.ChatRecyclerAdapter;

public class ChatActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    private static final String FIREBASE_URL = "https://android-chat.firebaseio-demo.com";
    private EditText editTextMsg;
    private ImageButton sendMsg;
    private List<Object> object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.rvChat);
        editTextMsg = (EditText) findViewById(R.id.outgoingMsg);
        sendMsg = (ImageButton) findViewById(R.id.button_send);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ChatRecyclerAdapter adapter = new ChatRecyclerAdapter(getList());
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(object.size()-1);
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.add(new Tsent(editTextMsg.getText().toString()));
                adapter.notifyItemInserted(object.size()-1);
                recyclerView.smoothScrollToPosition(object.size()-1);
            }
        });


    }

    private List<Object> getList() {
        object = new ArrayList<>();
        object.add(new Treceived("I there how are you dajfkldsjfa fkadjflkadjflkadsjf aldkfjalkdsfj asdfjaksdlf"));
        object.add(new Tsent("I am fine how r u? akfjasdfja ksdlfjadl fja;ldkfjakdfjalkdfkjaklfjalfjlaksdf jalsd"));
        object.add(new Treceived("full of surprise"));
        object.add(new Treceived("I there how are you"));
        object.add(new Tsent("I am fine how r u?"));
        object.add(new Treceived("full of surprise"));
        object.add(new Treceived("I there how are you"));
        object.add(new Tsent("I am fine how r u?"));
        object.add(new Treceived("full of surprise"));
        object.add(new Treceived("I there how are you"));
        object.add(new Tsent("I am fine how r u?"));
        object.add(new Treceived("full of surprise"));
        object.add(new Treceived("I there how are you"));
        object.add(new Tsent("I am fine how r u?"));
        object.add(new Treceived("full of surprise"));
        object.add(new Treceived("I there how are you"));
        object.add(new Tsent("I am fine how r u?"));
        object.add(new Treceived("full of surprise"));
        return object;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package jecrc.prtm.attendanceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.iid.zzd;

/**
 * Created by ghost on 15/2/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken=FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        L.lm("Refreshed token: " + refreshedToken);
    }

    private void sendTokenToServer(String token) {

    }

}

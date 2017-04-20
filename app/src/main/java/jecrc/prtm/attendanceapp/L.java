package jecrc.prtm.attendanceapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class L {

	public static void tm(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	public static void lm(String msg){
		Log.d("preetam", msg);
	}
	public static void tmlong(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
}

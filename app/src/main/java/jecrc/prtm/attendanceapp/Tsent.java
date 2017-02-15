package jecrc.prtm.attendanceapp;

import android.graphics.Bitmap;

/**
 * Created by ghost on 15/2/17.
 */

public class Tsent {
    private boolean msg;
    private String message;
    private Bitmap bitmap;

    Tsent(String message) {
        this.message = message;
    }

    Tsent(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public boolean isMsg() {
        return msg;
    }

    public void setMsg(boolean msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}

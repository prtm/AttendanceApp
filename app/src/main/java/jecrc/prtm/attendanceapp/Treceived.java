package jecrc.prtm.attendanceapp;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by ghost on 15/2/17.
 */

public class Treceived {
    private boolean msg=true;
    private String message;
    private Bitmap bitmap;

    Treceived(String message) {
        this.message = message;
    }

    Treceived(Bitmap bitmap) {
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

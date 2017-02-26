package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.Collections;
import java.util.List;

import jecrc.prtm.attendanceapp.DownloadStudent;
import jecrc.prtm.attendanceapp.R;

/**
 * Created by Ghost on 25-Aug-16.
 */
public class AttAdapter extends RecyclerView.Adapter<AttAdapter.MyAdapter> {
    private List<DownloadStudent> ls = Collections.emptyList();
    private LayoutInflater inflater;
    private boolean check[];
    private static final float imageA = 0.4f;
    private static final float imageB = 1f;

    public AttAdapter(Context context, List<DownloadStudent> ls, boolean majority) {
        this.ls = ls;
        inflater = LayoutInflater.from(context);
        check = new boolean[ls.size()];
        for (int i = 0; i < check.length; i++) {
            check[i] = majority;
        }
    }

    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAdapter(inflater.inflate(R.layout.custom_attendance, parent, false));
    }

    @Override
    public void onBindViewHolder(MyAdapter holder, int position) {
        String s = ls.get(position).getName();
        holder.textView.setText(s);
        TextDrawable drawable = TextDrawable.builder().beginConfig().withBorder(4).width(72).height(72).endConfig().buildRound(ls.get(position).getRollno(), ColorGenerator.MATERIAL.getRandomColor());
        holder.img.setImageDrawable(drawable);
        if (check[position]) {
            holder.img.setAlpha(imageA);
            holder.student_tick.setVisibility(View.VISIBLE);

        } else {
            holder.student_tick.setVisibility(View.GONE);
            holder.img.setAlpha(imageB);
        }

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    class MyAdapter extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView img, student_tick;


        MyAdapter(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.student_name);
            img = (ImageView) itemView.findViewById(R.id.student_img);
            student_tick = (ImageView) itemView.findViewById(R.id.student_tick);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (student_tick.getVisibility() == View.GONE) {
                        img.setAlpha(imageA);
                        student_tick.setVisibility(View.VISIBLE);
                        check[getAdapterPosition()] = true;
                    } else {
                        student_tick.setVisibility(View.GONE);
                        img.setAlpha(imageB);
                        check[getAdapterPosition()] = false;
                    }
                }
            });
        }
    }
}

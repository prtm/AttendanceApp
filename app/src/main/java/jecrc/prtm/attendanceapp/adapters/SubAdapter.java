package jecrc.prtm.attendanceapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.Collections;
import java.util.List;

import jecrc.prtm.attendanceapp.interfaces.SubInfo;
import jecrc.prtm.attendanceapp.models.DownloadClass;
import jecrc.prtm.attendanceapp.L;
import jecrc.prtm.attendanceapp.R;

/**
 * Created by Ghost on 25-Aug-16.
 */
public class SubAdapter extends RecyclerView.Adapter<SubAdapter.MyAdapter> {
    private List<DownloadClass> ls = Collections.emptyList();
    private LayoutInflater inflater;
    private ColorGenerator generator;
    private SubInfo subjectInformation;

    public SubAdapter(Context context, List<DownloadClass> ls) {
        this.ls = ls;
        this.inflater = LayoutInflater.from(context);
        generator = ColorGenerator.MATERIAL;
        subjectInformation = (SubInfo) context;
    }

    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAdapter(inflater.inflate(R.layout.custom_subject, parent, false));
    }

    @Override
    public void onBindViewHolder(MyAdapter holder, int position) {
        int color = generator.getRandomColor();
        String s = ls.get(position).getName();
        String x[] = s.split("\\s+");

        StringBuilder stringBuilder = new StringBuilder();
        for (String aX : x) {
            if (!aX.toLowerCase().equals("and")) {

                stringBuilder.append(aX.substring(0, 1));
            }
        }
        L.lm(stringBuilder.toString());
        TextDrawable drawable = TextDrawable.builder().buildRect(stringBuilder.toString(), color);
        holder.linearLayout.setBackgroundColor(color);
        holder.img.setImageDrawable(drawable);
        holder.textView.setText(s);

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyAdapter extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView textView;
        private LinearLayout linearLayout;

        public MyAdapter(final View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.circularSubject);
            textView = (TextView) itemView.findViewById(R.id.Subject_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.lv_ll);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    subjectInformation.itemClicked(ls.get(getLayoutPosition()).getClassId());
                }
            });
        }
    }
}

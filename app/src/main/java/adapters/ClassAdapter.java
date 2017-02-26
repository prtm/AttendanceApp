package adapters;

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

import interfaces.Class_info;
import jecrc.prtm.attendanceapp.DownloadClass;
import jecrc.prtm.attendanceapp.R;

/**
 * Created by Ghost on 25-Aug-16.
 */
public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.MyAdapter> {
    private List<DownloadClass> ls = Collections.emptyList();
    private LayoutInflater inflater;
    private ColorGenerator generator;
    private Class_info subjectInformation;

    public ClassAdapter(Context context, List<DownloadClass> ls) {
        this.ls = ls;
        this.inflater = LayoutInflater.from(context);
        generator = ColorGenerator.MATERIAL;
        subjectInformation = (Class_info) context;
    }

    @Override
    public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyAdapter(inflater.inflate(R.layout.custom_class, parent, false));
    }

    @Override
    public void onBindViewHolder(MyAdapter holder, int position) {
        int color = generator.getRandomColor();
        String s = ls.get(position).getName();
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(s.substring(0, 1), color);
        holder.ll.setBackgroundColor(color);
        holder.img.setImageDrawable(drawable);
        holder.textView.setText(s);
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyAdapter extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView img;
        private LinearLayout ll;

        public MyAdapter(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            img = (ImageView) itemView.findViewById(R.id.image_view);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    subjectInformation.onClicked(ls.get(getLayoutPosition()).getClassId());
                }
            });
        }
    }
}

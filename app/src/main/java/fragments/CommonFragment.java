package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jecrc.prtm.attendanceapp.DownloadStudent;
import jecrc.prtm.attendanceapp.R;

public class CommonFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "sn";
    public static List<DownloadStudent> listStudent;


    public CommonFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        int position = getArguments().getInt(ARG_SECTION_NUMBER);
        TextView rollno = (TextView) view.findViewById(R.id.att_roll);
        TextView name = (TextView) view.findViewById(R.id.att_name);
//        EventBus bus = EventBus.getDefault();
//        bus.register(this);
//        TextDrawable drawableAbsent = TextDrawable.builder().buildRound("A", R.color.colorAccent);
//        TextDrawable drawablePresent = TextDrawable.builder().buildRound("P", R.color.colorAccent);
//        present.setImageDrawable(drawablePresent);
//        absent.setImageDrawable(drawableAbsent);

        rollno.setText("Roll Number - " + listStudent.get(position).getRollno());
        name.setText(listStudent.get(position).getName());
        return view;
    }

//    @Subscribe
//    public void onEvent(ImageChanger imageChanger) {
//        if (imageChanger.imageChange) {
//            status.setImageResource(R.drawable.check);
//        } else {
//            status.setImageResource(R.drawable.cancel);
//        }
//    }
}


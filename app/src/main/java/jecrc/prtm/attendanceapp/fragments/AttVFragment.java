package jecrc.prtm.attendanceapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jecrc.prtm.attendanceapp.models.DownloadStudent;
import jecrc.prtm.attendanceapp.R;

public class AttVFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "sn";
    public static List<DownloadStudent> listStudent;
    private TextView checkedStatus;

    public AttVFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_att_view, container, false);
        int position = getArguments().getInt(ARG_SECTION_NUMBER);
        TextView rollno = (TextView) view.findViewById(R.id.att_roll);
        TextView name = (TextView) view.findViewById(R.id.att_name);
        checkedStatus = (TextView) view.findViewById(R.id.checkedStatus);
        /*EventBus bus = EventBus.getDefault();
        bus.register(this);
        TextDrawable drawableAbsent = TextDrawable.builder().buildRound("A", R.color.colorAccent);
        TextDrawable drawablePresent = TextDrawable.builder().buildRound("P", R.color.colorAccent);
        present.setImageDrawable(drawablePresent);
        absent.setImageDrawable(drawableAbsent);*/

        try {
            rollno.setText(String.format("Roll Number - %s", listStudent.get(position).getRollno()));
            name.setText(listStudent.get(position).getName());
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return view;
    }

//    @Override
//    public void onClicked(boolean b) {
//        //if b is true means present else absent
//        if (b) {
//            checkedStatus.setText(getResources().getString(R.string.present));
//        } else {
//            checkedStatus.setText(getResources().getString(R.string.absent));
//        }
//    }

//    @Subscribe
//    public void onEvent(ImageChanger imageChanger) {
//        if (imageChanger.imageChange) {
//            status.setImageResource(R.drawable.check);
//        } else {
//            status.setImageResource(R.drawable.cancel);
//        }
//    }
}


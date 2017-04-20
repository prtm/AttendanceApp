package jecrc.prtm.attendanceapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import jecrc.prtm.attendanceapp.interfaces.AttendanceStatusChange;
import jecrc.prtm.attendanceapp.R;


public class AttBFragment extends Fragment {
    private AttendanceStatusChange comm;

    public AttBFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_att_button, container, false);
        comm = (AttendanceStatusChange) getActivity();

        //final EventBus bus = EventBus.getDefault();

//        commonF= (ButtonF_to_CommonF) getActivity();
        Button present = (Button) view.findViewById(R.id.present);
        Button absent = (Button) view.findViewById(R.id.absent);
        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comm.statusSet(true);
//                commonF.onImageSet(true);
//                bus.post(new ImageChanger(true));
            }
        });
        absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comm.statusSet(false);
//                commonF.onImageSet(false);
//                bus.post(new ImageChanger(false));

            }
        });
        return view;
    }


}

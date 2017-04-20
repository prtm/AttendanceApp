package jecrc.prtm.attendanceapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jecrc.prtm.attendanceapp.R;

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private String[] list;
    private String[] list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = (SearchView) findViewById(R.id.searchView);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvSearch);
        list2 = getResources().getStringArray(R.array.names);
        list=list2;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final RecyclerView.Adapter adapter = new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new SearchViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                TextView textView = (TextView) holder.itemView;
                textView.setText(list[position]);
            }

            @Override
            public int getItemCount() {
                return list.length;
            }
        };
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<String> s=sortedlist(list2,newText);
                String[] str=new String[s.size()];
                for(int i=0;i<s.size();i++){
                    str[i]=s.get(i);
                }
                list=str;
                adapter.notifyDataSetChanged();
                return true;
            }
        });


    }

    private List<String> sortedlist(String[] list, String newText) {
        List<String> str = new ArrayList<>();
        for (String aList : list) {
            String s = aList.toLowerCase();
            if (s.contains(newText.toLowerCase())) {
                str.add(s);
            }
        }
        return str;
    }

    private class SearchViewHolder extends RecyclerView.ViewHolder {

        SearchViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}

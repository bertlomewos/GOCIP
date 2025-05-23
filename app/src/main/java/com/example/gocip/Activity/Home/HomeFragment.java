package com.example.gocip.Activity.Home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gocip.R;
import com.example.gocip.databinding.FragmentHomeBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    RecyclerView rv;

    ArrayList<String> dataSource;
    LinearLayoutManager linearLayoutManager;
    MyRVAdapter myRVAdapter;
    ListView L_listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = binding.TopLessionsRv;
        dataSource = new ArrayList<>();
        dataSource.add("Jello");
        dataSource.add("Jello");
        dataSource.add("Jello");
        dataSource.add("Jello");
        dataSource.add("Jello");
        dataSource.add("Jello");
        dataSource.add("Jello");
        dataSource.add("Jello");
        dataSource.add("Jello");
        dataSource.add("Jello");
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        myRVAdapter = new MyRVAdapter(dataSource);
        MylIstadapter mylIstadapter = new MylIstadapter(getContext(), 0, dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRVAdapter);
        binding.LessionListView.setAdapter(mylIstadapter);

    }
    public  class MylIstadapter extends ArrayAdapter{

        public MylIstadapter(@NonNull Context context, int resource, ArrayList<String> data) {
            super(context, resource, data);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView  = LayoutInflater.from(getContext()).inflate(R.layout.lession_item  , parent, false);
            }
            TextView text = convertView.findViewById(R.id.Lession_Name);
            text.setText(dataSource.get(position));

            return convertView;
        }
    }
    public class MyRVAdapter extends RecyclerView.Adapter<MyRVAdapter.MyHolder> {
        ArrayList<String> data;

        public MyRVAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lession_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{

            public MyHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

    }

}
package br.com.andersonaltissimo.myguests.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.andersonaltissimo.myguests.R;
import br.com.andersonaltissimo.myguests.adapters.GuestListAdapter;


public class AllInvitedFragment extends Fragment {

    private ViewHolder vh = new ViewHolder();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_invited, container, false);
        Context context = view.getContext();

        //Obter RecycleView
        this.vh.recyclerView = (RecyclerView) view.findViewById(R.id.recycle_all_invited);

        // Definir um Adapter
        GuestListAdapter guestListAdapter = new GuestListAdapter();
        this.vh.recyclerView.setAdapter(guestListAdapter);


        //Definir um Layout
        this.vh.recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    public static class ViewHolder {
        RecyclerView recyclerView;
    }
}

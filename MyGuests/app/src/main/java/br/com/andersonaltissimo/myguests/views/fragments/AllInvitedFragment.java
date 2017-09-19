package br.com.andersonaltissimo.myguests.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.andersonaltissimo.myguests.R;
import br.com.andersonaltissimo.myguests.adapters.GuestListAdapter;
import br.com.andersonaltissimo.myguests.business.GuestBusiness;
import br.com.andersonaltissimo.myguests.constants.GuestConstants;
import br.com.andersonaltissimo.myguests.entities.Guest;
import br.com.andersonaltissimo.myguests.listeners.OnGuestInteractionListener;
import br.com.andersonaltissimo.myguests.views.activities.GuestFormActivity;


public class AllInvitedFragment extends Fragment {

    private ViewHolder vh = new ViewHolder();
    private GuestBusiness guestBusiness;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_invited, container, false);
        final Context context = view.getContext();

        this.guestBusiness = new GuestBusiness(context);

        OnGuestInteractionListener listener = new OnGuestInteractionListener() {
            @Override
            public void onListClick(int id) {
                //Abrir Activity de Formulario

                Bundle bundle = new Bundle();
                bundle.putInt(GuestConstants.GUEST_ID, id);

                Intent intent = new Intent(getContext(), GuestFormActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int id) {

            }
        };


        List<Guest> lstGuest = this.guestBusiness.getInvited();

        //Obter RecycleView
        this.vh.recyclerView = (RecyclerView) view.findViewById(R.id.recycle_all_invited);

        // Definir um Adapter
        GuestListAdapter guestListAdapter = new GuestListAdapter(lstGuest, listener);
        this.vh.recyclerView.setAdapter(guestListAdapter);


        //Definir um Layout
        this.vh.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        return view;
    }

    public static class ViewHolder {
        RecyclerView recyclerView;
    }
}

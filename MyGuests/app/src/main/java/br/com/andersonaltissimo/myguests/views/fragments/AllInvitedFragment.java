package br.com.andersonaltissimo.myguests.views.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.andersonaltissimo.myguests.R;
import br.com.andersonaltissimo.myguests.adapters.GuestListAdapter;
import br.com.andersonaltissimo.myguests.business.GuestBusiness;
import br.com.andersonaltissimo.myguests.constants.GuestConstants;
import br.com.andersonaltissimo.myguests.entities.Guest;
import br.com.andersonaltissimo.myguests.entities.GuestCount;
import br.com.andersonaltissimo.myguests.listeners.OnGuestInteractionListener;
import br.com.andersonaltissimo.myguests.views.activities.GuestFormActivity;


public class AllInvitedFragment extends Fragment {
    private ViewHolder vh = new ViewHolder();
    private GuestBusiness guestBusiness;
    private OnGuestInteractionListener onGuestInteractionListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();

        this.loadDashboard();
        this.loadGuests();
    }

    private void loadGuests() {
        List<Guest> lstGuest = this.guestBusiness.getInvited();

        // Definir um Adapter
        GuestListAdapter guestListAdapter = new GuestListAdapter(lstGuest, this.onGuestInteractionListener);
        this.vh.recyclerView.setAdapter(guestListAdapter);
        guestListAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_invited, container, false);
        final Context context = view.getContext();

        this.guestBusiness = new GuestBusiness(context);

        this.onGuestInteractionListener = new OnGuestInteractionListener() {
            @Override
            public void onListClick(int id) {
                //Abrir Activity de Formulario

                Bundle bundle = new Bundle();
                bundle.putInt(GuestConstants.BundleConstants.GUEST_ID, id);

                Intent intent = new Intent(getContext(), GuestFormActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int id) {
                boolean deletou = guestBusiness.remove(id);

                Toast.makeText(getContext(), getString(R.string.guest_removed), Toast.LENGTH_LONG);

                loadDashboard();
                loadGuests();
            }
        };

        //Obter RecycleView
        this.vh.recyclerView = (RecyclerView) view.findViewById(R.id.recycle_all_invited);

        //Definir um Layout
        this.vh.recyclerView.setLayoutManager(new LinearLayoutManager(context));

        this.vh.qtd_absent = view.findViewById(R.id.qtd_absent);
        this.vh.qtd_present = view.findViewById(R.id.qtd_present);
        this.vh.qtd_all_invited = view.findViewById(R.id.qtd_all_invited);

        return view;
    }

    private void loadDashboard() {
        GuestCount guestCount = this.guestBusiness.loadDashboard();

        this.vh.qtd_all_invited.setText(String.valueOf(guestCount.getAllInvitedCount()));
        this.vh.qtd_present.setText(String.valueOf(guestCount.getPresentCount()));
        this.vh.qtd_absent.setText(String.valueOf(guestCount.getAbsentCount()));
    }

    private static class ViewHolder {
        RecyclerView recyclerView;
        TextView qtd_present;
        TextView qtd_absent;
        TextView qtd_all_invited;
    }
}

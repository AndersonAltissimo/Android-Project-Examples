package br.com.andersonaltissimo.myguests.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.andersonaltissimo.myguests.R;
import br.com.andersonaltissimo.myguests.entities.Guest;
import br.com.andersonaltissimo.myguests.listeners.OnGuestInteractionListener;
import br.com.andersonaltissimo.myguests.viewholders.GuestViewHolder;

public class GuestListAdapter extends RecyclerView.Adapter<GuestViewHolder>{

    private List<Guest> guestList;
    private OnGuestInteractionListener listener;

    public GuestListAdapter(List<Guest> lstGuest, OnGuestInteractionListener listener) {
        this.guestList = lstGuest;
        this.listener = listener;
    }

    @Override
    public GuestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View guestView = layoutInflater.inflate(R.layout.row_guest_list, parent, false);

        return new GuestViewHolder(guestView);
    }

    @Override
    public void onBindViewHolder(GuestViewHolder holder, int position) {
        Guest guest = this.guestList.get(position);
        holder.bindData(guest, this.listener);
    }

    @Override
    public int getItemCount() {
        return this.guestList.size();
    }
}

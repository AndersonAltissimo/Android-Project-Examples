package br.com.andersonaltissimo.myguests.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.andersonaltissimo.myguests.R;
import br.com.andersonaltissimo.myguests.entities.Guest;
import br.com.andersonaltissimo.myguests.listeners.OnGuestInteractionListener;


public class GuestViewHolder extends RecyclerView.ViewHolder {

    private TextView textName;

    public GuestViewHolder(View itemView) {
        super(itemView);

        this.textName = (TextView) itemView.findViewById(R.id.textView);
    }

    public void bindData(final Guest guest, final OnGuestInteractionListener listener) {
        this.textName.setText(guest.getName());

        this.textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(guest.getId());
            }
        });
    }
}

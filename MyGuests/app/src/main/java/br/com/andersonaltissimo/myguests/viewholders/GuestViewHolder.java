package br.com.andersonaltissimo.myguests.viewholders;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.andersonaltissimo.myguests.R;
import br.com.andersonaltissimo.myguests.entities.Guest;
import br.com.andersonaltissimo.myguests.listeners.OnGuestInteractionListener;


public class GuestViewHolder extends RecyclerView.ViewHolder {

    private TextView textName;
    private Context context;

    public GuestViewHolder(View itemView, Context context) {
        super(itemView);

        this.textName = (TextView) itemView.findViewById(R.id.txt_row_name);
        this.context = context;
    }

    public void bindData(final Guest guest, final OnGuestInteractionListener listener) {
        this.textName.setText(guest.getName());

        this.textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onListClick(guest.getId());
            }
        });

        this.textName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                new AlertDialog.Builder(context)
                        .setTitle(R.string.remove_guest_title_alert)
                        .setMessage(R.string.remove_guest_text_alert)
                        .setIcon(R.drawable.ic_menu_camera)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listener.onDeleteClick(guest.getId());
                            }
                        })
                        .setNeutralButton(R.string.no, null)
                        .show();

                return true;
            }
        });
    }
}

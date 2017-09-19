package br.com.andersonaltissimo.myguests.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.andersonaltissimo.myguests.R;

/**
 * Created by anderson on 18/09/17.
 */

public class GuestViewHolder extends RecyclerView.ViewHolder {

    private TextView textName;

    public GuestViewHolder(View itemView) {
        super(itemView);

        this.textName = (TextView) itemView.findViewById(R.id.textView);
    }
}

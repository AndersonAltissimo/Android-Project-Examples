package br.com.andersonaltissimo.myguests.business;

import android.content.Context;

import java.util.List;

import br.com.andersonaltissimo.myguests.constants.DatabaseConstants;
import br.com.andersonaltissimo.myguests.entities.Guest;
import br.com.andersonaltissimo.myguests.repositories.GuestRepository;

public class GuestBusiness {
    private GuestRepository guestRepository;

    public GuestBusiness(Context context){
        this.guestRepository = GuestRepository.getInstance(context);
    }

    public Boolean Insert(Guest guest){
        return this.guestRepository.insert(guest);
    }

    public List<Guest> getInvited() {
        return this.guestRepository.getGuestByQuery("select * from " + DatabaseConstants.GUEST.TABLE_NAME);
    }
}

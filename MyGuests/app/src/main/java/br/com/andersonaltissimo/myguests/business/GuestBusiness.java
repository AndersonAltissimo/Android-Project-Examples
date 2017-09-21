package br.com.andersonaltissimo.myguests.business;

import android.content.Context;

import java.util.List;

import br.com.andersonaltissimo.myguests.constants.DatabaseConstants;
import br.com.andersonaltissimo.myguests.constants.GuestConstants;
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

    public List<Guest> getAbsent() {
        return this.guestRepository.getGuestByQuery("select * from " + DatabaseConstants.GUEST.TABLE_NAME + " where "
                + DatabaseConstants.GUEST.COLUMNS.PRESENCE +" = " + GuestConstants.CONFIRMATION.ABSENT);
    }

    public List<Guest> getPresent() {
        return this.guestRepository.getGuestByQuery("select * from " + DatabaseConstants.GUEST.TABLE_NAME + " where "
                + DatabaseConstants.GUEST.COLUMNS.PRESENCE +" = " + GuestConstants.CONFIRMATION.PRESENT);
    }

    public Guest load(int id) {
        return this.guestRepository.load(id);
    }
}

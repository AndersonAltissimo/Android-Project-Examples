package br.com.andersonaltissimo.myguests.business;

import android.content.Context;

import br.com.andersonaltissimo.myguests.entities.Guest;
import br.com.andersonaltissimo.myguests.repositories.GuestRepository;

public class GuestBusiness {
    private GuestRepository guestRepository;

    public GuestBusiness(Context context){
        this.guestRepository = GuestRepository.getInstance(context);
    }

    public Boolean Insert (Guest guest){
       return this.guestRepository.insert(guest);
    }
}

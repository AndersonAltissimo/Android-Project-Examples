package br.com.andersonaltissimo.myguests.entities;

/**
 * Created by andersonaltissimo on 10/30/17.
 */

public class GuestCount {
    public GuestCount(int presentCount, int absentCount, int allInvitedCount) {
        this.presentCount = presentCount;
        this.absentCount = absentCount;
        this.allInvitedCount = allInvitedCount;
    }

    private int presentCount;
    private int absentCount;
    private int allInvitedCount;

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    public int getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(int absentCount) {
        this.absentCount = absentCount;
    }

    public int getAllInvitedCount() {
        return allInvitedCount;
    }

    public void setAllInvitedCount(int allInvitedCount) {
        this.allInvitedCount = allInvitedCount;
    }
}

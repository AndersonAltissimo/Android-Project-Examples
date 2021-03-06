package br.com.andersonaltissimo.myguests.entities;

public class Guest {
    private int id;
    private String name;
    private int confirmed;

    public Guest(int id, String name, int confirmed) {
        this.id = id;
        this.name = name;
        this.confirmed = confirmed;
    }

    public Guest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }
}

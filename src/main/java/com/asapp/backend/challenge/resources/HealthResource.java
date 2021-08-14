package com.asapp.backend.challenge.resources;

public class HealthResource {

    private String health;

    public HealthResource(String health) {
        this.health = health;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

}

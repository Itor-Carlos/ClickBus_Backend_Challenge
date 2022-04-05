package com.clickbus.ClickBus_Challenge.dto;

import javax.validation.constraints.NotBlank;

public class PlaceDTO {

    @NotBlank(message = " The attribute 'name' from the Place class can't be blank ")//check if the name is empty or null
    private String name;
    @NotBlank(message = " The attribute 'city' from the Place class can't be blank ")//check if the city is empty or null
    private String city;
    @NotBlank(message = " The attribute 'state' from the Place class can't be blank ")//check if the state is empty or null
    private String state;

    public PlaceDTO() {
    }

    public PlaceDTO(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

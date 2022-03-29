package com.clickbus.ClickBus_Challenge.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "places")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = false,nullable = false,length = 60)
    private String name;
    @Column(unique = false,nullable = false,length = 40)
    private String city;
    @Column(unique = false,nullable = false,length = 50)
    private String state;

    @Column(unique = true,nullable = false,length = 160)
    private String slug;

    @Column(unique = false,nullable = false)
    private Date createdAt;
    @Column(unique = false,nullable = false)
    private Date updateAt;

    public Place() {}

    public Place(String name, String city, String state, Date createdAt, Date updateAt) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.setSlug(this.createSlug());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    private String createSlug(){
        return (this.getName()+"-"+this.getCity()+"-"+this.getState()).toLowerCase().replace(" ", "-");
    }
}

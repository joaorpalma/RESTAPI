package com.simplenotes.RESTAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "name")
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Column(name = "email")
    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Column(name = "password")
    @NotNull
    @JsonIgnore
    private String password;

    @Column(name = "photo")
    private String photo;

    public String getPhoto(){
        return photo;
    }

    public void setPhoto(String photo){
        this.photo = photo;
    }

    @Column(name = "pushnotificationid")
    private String pushnotificationid;

    public String getPushNotificationId(){
        return pushnotificationid;
    }

    public void setPushNotificationId(String pushnotificationid){
        this.pushnotificationid = pushnotificationid;
    }
}

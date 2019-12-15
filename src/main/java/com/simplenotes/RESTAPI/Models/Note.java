package com.simplenotes.RESTAPI.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "note")
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    @Column(name = "title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    @Column(name = "content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    @Column(name = "lastedit")
    private String lastedit;

    public String getLastEdit() {
        return lastedit;
    }

    public void setLastEdit(String lastedit){
        this.lastedit = lastedit;
    }

    @Column(name = "location")
    private String location;

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }
}

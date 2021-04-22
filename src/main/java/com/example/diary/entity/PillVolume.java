package com.example.diary.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "pillvolume")
public class PillVolume {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 64)
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="pill_id", nullable=false)
    private Pill pill;

    //how much was eaten, grams or pieces
    @Column
    private double volume;

    @ManyToOne
    @JoinColumn(name="pillrecord_id", nullable=false)
    @JsonIgnore
    private PillRecord pillRecord;

    @Override
    public String toString() {
        return "PillVolume{" +
                "id='" + id + '\'' +
                ", pill=" + pill +
                ", volume=" + volume +
                '}';
    }
}

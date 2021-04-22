package com.example.diary.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pillrecord")
public class PillRecord extends Record{

    @OneToMany(cascade = CascadeType.ALL, mappedBy="pillRecord")
    private List<PillVolume> pillVolumes;

    @Override
    public String toString() {
        return "PillRecord{" +
                "pillVolumes=" + pillVolumes +
                "} " + super.toString();
    }
}

package com.example.diary.entity;

import com.example.diary.enums.BodyResponse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "bodyresponserecord")
public class BodyResponseRecord extends Record{
    @Column(name = "generalstate")
    @Enumerated(EnumType.STRING)
    private BodyResponse generalState;

    @Column
    @Enumerated(EnumType.STRING)
    private BodyResponse nose;

    @Column
    @Enumerated(EnumType.STRING)
    private BodyResponse eyes;

    @Column
    @Enumerated(EnumType.STRING)
    private BodyResponse chin;

    @Column
    @Enumerated(EnumType.STRING)
    private BodyResponse forehead;
}

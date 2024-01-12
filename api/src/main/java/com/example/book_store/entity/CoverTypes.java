package com.example.book_store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CoverTypes")
public class CoverTypes extends SuperEntity{

    private String name;
}

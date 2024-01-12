package com.example.book_store.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "Company")
public class Company extends SuperEntity{

    private String name;

}

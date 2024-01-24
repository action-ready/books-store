package com.example.book_store.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "Tokens")
public class Token extends SuperEntity{

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String key;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredDate;




    public enum Type {
        REFRESH_TOKEN, REGISTER, FORGOT_PASSWORD
    }
}

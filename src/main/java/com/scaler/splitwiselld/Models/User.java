package com.scaler.splitwiselld.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel {

    private String username;

    private String password;

    private String phoneNumber;

    @ManyToMany(mappedBy = "members")

    private List<Group> groups;

    @Enumerated(EnumType.ORDINAL)
    private UserStatus userStatus;

}

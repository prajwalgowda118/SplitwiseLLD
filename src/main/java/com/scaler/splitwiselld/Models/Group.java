package com.scaler.splitwiselld.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@Entity
@Entity(name = "user_groups")
public class Group extends BaseModel{

        private String groupName;
        private String groupDescription;
        @ManyToMany
        private List<User> members;
        @ManyToOne
        private User createdBy;

}

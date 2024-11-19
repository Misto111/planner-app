package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "priorities")
public class Priority extends BaseEntity {

    @Column(unique = true, nullable = false)
    private PriorityName name;

    @Column(nullable = false)
    private String description;

    @OneToMany
    private Set<Task> tasks;

    public PriorityName getName() {
        return name;
    }

    public Priority setName(PriorityName name) {
        this.name = name;
        setDescription(name);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Priority setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Priority setTasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    private void setDescription(PriorityName name) {
        String description = "";

        switch (name) {
            case URGENT -> description = "An urgent problem that blocks the system use until the issue is resolved.";
            case IMPORTANT -> description = "A core functionality that your product is explicitly supposed to perform is compromised.";
            case LOW -> description = "Should be fixed if time permits but can be postponed.";
        }

        this.description = description;
    }
}

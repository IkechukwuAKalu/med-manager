package com.ikechukwuakalu.med_manager.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Medication {

    @PrimaryKey(autoGenerate = true) private int id;
    @ColumnInfo(name = "name") private String name;
    @ColumnInfo(name = "start_date") private long startDate;
    @ColumnInfo(name = "end_date") private long endDate;
    @ColumnInfo(name = "interval") private String interval;
    @ColumnInfo(name = "description") private String description;
    @ColumnInfo(name = "created_at") private long createdAt;

    Medication() {}

    @Ignore
    public Medication(String name, long startDate, long endDate, String interval, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.interval = interval;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medication)) return false;
        Medication medication = (Medication) o;
        return (getName().equals(medication.getName()) &&
                (getStartDate() == medication.getStartDate()) &&
                (getEndDate() == medication.getEndDate()) &&
                (getInterval().equals(medication.getInterval())) &&
                (getDescription().equals(medication.getDescription())));
    }

    @Override
    public String toString() {
        return "Medication(name: " + getName() +
                " startDate: " + getStartDate() +
                " endDate: " + getEndDate() +
                " interval: " + getInterval() +
                " description: " + getDescription() + ")";
    }
}

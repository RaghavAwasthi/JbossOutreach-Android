package com.org.jbossoutreach.Models;

public class RepositoryModel {
    private String name,description;
    int commitcount,forkcunt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCommitcount() {
        return commitcount;
    }

    public void setCommitcount(int commitcount) {
        this.commitcount = commitcount;
    }

    public int getForkcunt() {
        return forkcunt;
    }

    public void setForkcunt(int forkcunt) {
        this.forkcunt = forkcunt;
    }
}

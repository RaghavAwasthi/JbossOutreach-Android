package com.org.jbossoutreach.Models;

public class RepositoryModel {
     String name;
     String description;

    public RepositoryModel() {
    }

    String contributionurl;
    int commitcount,forkcunt;

    public String getContributionurl() {
        return contributionurl;
    }

    public void setContributionurl(String contributionurl) {
        this.contributionurl = contributionurl;
    }

    public RepositoryModel(String name, String description, String contributionurl, int commitcount, int forkcunt) {
        this.name = name;
        this.description = description;
        this.contributionurl = contributionurl;
        this.commitcount = commitcount;
        this.forkcunt = forkcunt;
    }

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

package com.org.jbossoutreach.Models

class RepositoryModel {
    var name: String? = null
    var description: String? = null

    constructor() {}

    var contributionurl: String? = null
    var commitcount = 0
    var forkcunt = 0

    constructor(name: String?, description: String?, contributionurl: String?, commitcount: Int, forkcunt: Int) {
        this.name = name
        this.description = description
        this.contributionurl = contributionurl
        this.commitcount = commitcount
        this.forkcunt = forkcunt
    }
}
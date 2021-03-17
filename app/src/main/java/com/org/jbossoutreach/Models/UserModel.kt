package com.org.jbossoutreach.Models

class UserModel {
    var name: String? = null
    var image_url: String? = null

    constructor() {}
    constructor(name: String?, image_url: String?) {
        this.name = name
        this.image_url = image_url
    }
}
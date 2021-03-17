package com.org.jbossoutreach.chat

class ChatMessage {
    var text: String? = null
    var name: String? = null
    var photoUrl: String? = null

    constructor() {}
    constructor(text: String?, name: String?, photoUrl: String?) {
        this.text = text
        this.name = name
        this.photoUrl = photoUrl
    }
}
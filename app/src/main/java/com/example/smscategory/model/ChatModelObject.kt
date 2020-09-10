package com.example.smscategory.model

class ChatModelObject : ListObject() {
    private var chatModel: Sms? = null
    fun getChatModel(): Sms? {
        return chatModel
    }

    fun setChatModel(chatModel: Sms?) {
        this.chatModel = chatModel
    }

    override fun getType(): Int {
      return TYPE_MESSAGE
        }

}
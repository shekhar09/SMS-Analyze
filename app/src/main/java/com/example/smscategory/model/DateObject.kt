package com.example.smscategory.model

class DateObject : ListObject() {
    var date: String? = null

    override fun getType(): Int {
        return TYPE_DATE
    }
}
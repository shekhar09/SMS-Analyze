package com.example.smscategory.model

abstract class ListObject {
    abstract fun getType(): Int

    companion object {
        const val TYPE_DATE = 0
        const val TYPE_MESSAGE = 1

    }
}
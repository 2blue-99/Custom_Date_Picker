package com.example.custom_date_picker

object AppSize {
    private var SCREEN_HEIGHT = 0
    private var SCREEN_WIDTH = 0

    private var _ITEM_LIST_HEIGHT = 0
    val ITEM_LIST_HEIGHT get() = _ITEM_LIST_HEIGHT

    fun initialize(width: Int, height: Int){
        SCREEN_HEIGHT = height
        SCREEN_HEIGHT = width
        _ITEM_LIST_HEIGHT = height / 46
    }
}
package com.example.custom_date_picker

enum class PriceType(
    val value: String,
    val price: String
) {
    PRICE_NONE("금액선택", "0"),
    PRICE_5000("5,000원", "5000"),
    PRICE_10000("10,000원", "10000"),
    PRICE_20000("20,000원", "20000"),
    PRICE_30000("30,000원", "30000"),
    PRICE_40000("40,000원", "40000"),
    PRICE_50000("50,000원", "50000"),
    PRICE_60000("60,000원", "60000"),
    PRICE_70000("70,000원", "70000"),
    PRICE_80000("80,000원", "80000"),
    PRICE_90000("90,000원", "90000"),
    PRICE_100000("100,000원", "100000"),
    PRICE_200000("200,000원", "200000"),
    PRICE_300000("300,000원", "300000");

    fun toList() : List<String> {
        return values().map { type ->  type.value}
    }

    fun toPosition() : Int {
        return values().indexOf(this)
    }

    companion object {
        fun toList() : List<String> {
            return values().map { type ->  type.value}
        }

        fun toList(list: List<String>) : List<String> {
            return values().filter { type ->
                type.price == list.find { it == type.price} || type.price == "0"
            }.map { type ->  type.value }
        }

        fun toType(position: Int) : PriceType {
            return values()[position]
        }

        fun toType(value: String) : PriceType {
            return values().find { it.price == value }?: PRICE_NONE
        }

        fun toTypeValue(value: String) : PriceType {
            return values().find { it.value == value }?: PRICE_NONE
        }
    }
}
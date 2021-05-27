package com.rsschool.android2021

class Range() {
    var min: Int = 0
    var max: Int = 0

    constructor(min: String, max: String) : this() {
        try {
            this.min = min.toInt()
            this.max = max.toInt()
        } catch (e: Exception) {
            throw IllegalArgumentException("Wrong number format ${e.message}")
        }
    }
}

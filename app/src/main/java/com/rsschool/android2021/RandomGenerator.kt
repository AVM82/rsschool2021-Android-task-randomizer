package com.rsschool.android2021

import kotlin.random.Random

object RandomGenerator {
    private const val min = 0
    private const val max = 255

    fun generate(range: Range): Int {
        return Random.nextInt(range.min, range.max)
    }

    fun validateRange(range: Range): Range {
        return when {
            range.min > range.max -> {
                throw IllegalArgumentException("The minimum value cannot be greater than the maximum")
            }
            range.min !in min..max -> {
                throw IllegalArgumentException("The minimum value is out of range")
            }
            range.max !in min..max -> {
                throw IllegalArgumentException("The maximum value is out of range")
            }
            else -> range
        }
    }
}
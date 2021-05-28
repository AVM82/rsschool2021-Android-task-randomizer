package com.rsschool.android2021

import kotlin.random.Random

object RandomGenerator {
    private const val min = 0
    private const val max = 255

    fun generate(range: Range): Int {
        return Random.nextInt(range.min, range.max)
    }

    /**
     * Checks the object of Range type to against valid conditions.
     */
    fun validateRange(range: Range): Range {
        return when {
            range.min > range.max -> {
                throw IllegalArgumentException("The minimum value cannot be greater than the maximum")
            }
            range.min == range.max -> {
                throw IllegalArgumentException("The minimum value cannot be equal to the maximum value.")
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

    /**
     * Returns an object of the Range type with the specified boundary values to generate a random number
     */
    fun getRequiredRange(min: String, max: String): Range {
        try {
            return Range(min.toInt(), max.toInt())
        } catch (e: Exception) {
            throw IllegalArgumentException("Wrong number format ${e.message}")
        }
    }

    /**
     * Returns an object of Range type with valid boundary values to generate a random number
     */
    fun getValidRange(): Range {
        return Range(this.min, this.max)
    }

}
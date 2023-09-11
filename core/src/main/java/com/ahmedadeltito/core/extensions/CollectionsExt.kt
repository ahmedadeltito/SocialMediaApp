package com.ahmedadeltito.core.extensions

/**
 * Extension function to check if [List] is equal to another [List]
 */
fun <T> List<T>.isEqual(second: List<T>): Boolean {
    if (this.size != second.size) {
        return false
    }
    return this.zip(second).all { (x, y) -> x == y }
}
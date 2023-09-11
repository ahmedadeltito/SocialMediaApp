package com.ahmedadeltito.core.mapper

/**
 * It is the directional mapping contract class that maps the given input to the given out
 */
interface Mapper<in I, out O> {
    fun map(input: I): O
}
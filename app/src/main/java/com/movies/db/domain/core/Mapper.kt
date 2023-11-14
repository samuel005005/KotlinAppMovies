package com.movies.db.domain.core


import java.util.AbstractList

abstract class Mapper<T1, T2> {

    abstract fun map(value: T1): T2

    abstract fun reverseMap(value: T2): T1
    open fun map(values: AbstractList<T1>?): ArrayList<T2>? {
        val returnValues = ArrayList<T2>()
        if (values == null) return returnValues
        for (value in values) returnValues.add(map(value))
        return returnValues
    }

    open fun reverseMap(values: ArrayList<T2>?): AbstractList<T1>? {
        val returnValues: AbstractList<T1> = ArrayList()
        if (values == null) return returnValues
        for (value in values) returnValues.add(reverseMap(value))
        return returnValues
    }
}
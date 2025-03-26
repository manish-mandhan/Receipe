package com.manish.mandhan.receipe

import org.junit.Test

import org.junit.Assert.*


class MyList<T> : ArrayList<T>() {
    private var lastRemoveItem: T? = null

    override fun removeAt(index: Int): T {
        lastRemoveItem = get(index)

        return super.removeAt(index)
    }

    override fun remove(element: T): Boolean {
        lastRemoveItem = element
        return super.remove(element)
    }

    fun getLastRemovedItem() = lastRemoveItem
}


class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {


        val myList: MyList<Int> = MyList()

        myList.add(10)
        myList.add(20)
        myList.add(30)

        myList.remove(0)

        assertEquals(10, myList.getLastRemovedItem())

    }
}
package com.robinvdb.ai.tree

data class Node<T>(val value: T, val heurastic: Int) : Iterable<Node<T>> {
    override fun iterator() = children.iterator()

    val children = mutableListOf<Node<T>>()
    var visited = false
}
package com.kotlin.mykotlinproj.data.repo.cache

class SaveResultKey(
    private val searchQuery: String,
    private val position: Int
) : Comparable<SaveResultKey> {

    override fun compareTo(other: SaveResultKey): Int {
        return if (position == other.position && searchQuery.equals(
                other.searchQuery,
                true
            )
        ) {
            0
        } else if (position < other.position) {
            -1
        } else 1
    }

    override fun equals(other: Any?): Boolean {
        if (other is SaveResultKey) {
            if (this.position == other.position && this.searchQuery == other.searchQuery) {
                return true
            }
        }
        return false
    }

}
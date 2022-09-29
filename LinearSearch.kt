package search

fun <T : Comparable<T>> linearSearch(array: Array<T>, key: T): Int {
    return linearSearchImpl(array, key)
}

fun <T : Comparable<T>> linearSearchImpl(array: Array<T>, key: T): Int {
    for (i in array.indices) {
        if (array[i].compareTo(key) == 0) {
            return i
        }
    }

    return -1
}

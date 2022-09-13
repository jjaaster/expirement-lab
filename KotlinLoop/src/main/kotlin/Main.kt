fun main(args: Array<String>) {
    val array1 = arrayOf("town", "multi-tier", "B-to-B", "pervasive")
    val array2 = arrayOf("empowered", "leveraged", "alligned", "targeted")
    val array3 = arrayOf("Process", "equation", "linear", "solution", "portal")

    val arraySize1 = array1.size
    val arraySize2 = array2.size
    val arraySize3 = array3.size

    val rand1 = (Math.random() * arraySize1).toInt()
    val rand2 = (Math.random() * arraySize2).toInt()
    val rand3 = (Math.random() * arraySize3).toInt()

    val phrase = "${array1[rand1]} ${array2[rand2]} ${array3[rand3]}"
    println(phrase)

  //  val rand1 = (Math.random() * arraySize1).toInt ()
    println("Program arguments: ${args.joinToString()}")
}
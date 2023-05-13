import java.io.File
import java.io.InputStream

fun main(){
    val inputStream: InputStream = File("src/inputs/input.txt").inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }

    var sum = 0
    var output = mutableListOf<Int>()

    val vector = inputString.split("\n").map{it.trim()}.map{
        if (it != "")
        {sum+=it.toInt();}
        else
        { if(sum!=0) {output.add(sum)} ; sum = 0}
        sum}

    if (vector[vector.size-1]!=0){output.add(sum)}
    val higher = output.reduce{ x,y -> if(x > y) {x} else {y} }




}
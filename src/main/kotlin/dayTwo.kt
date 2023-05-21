import java.io.File
import java.io.InputStream
import kotlin.math.round


fun main(){
    operator fun String.component1()=this[0]
    operator fun String.component2()=this[1]

    val inputStream: InputStream = File("src/inputs/inputTwo.txt").inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }

    println(inputString)

    val list = mutableListOf<String>()
    val listTwo = mutableListOf<String>()
    val listThree = listOf<String>("AY","BX","CZ")
    val listFour = mutableListOf<String>()

    inputString.toList().forEachIndexed{ index, element -> if(index%2==0){list.add(element.toString()) } }

    /*
    * questão -> percorrer uma lista acessando o indice e verificando o próximo elemento
    * REFATORAR ESSES IFS
    * */

    val iterator = list.listIterator()
    iterator.forEach { if((it.indexOf(it) % 2) == 0) {listTwo.add(it+iterator.next()) }}
    var sum = 0

    listTwo.forEach {
        if (it[1]=='X'){
            sum+=1
        }
        if(it[1]=='Y'){
            sum+=2
        }
        if(it[1]=='Z'){
            sum+=3
        }
        if(it[0]=='A' && it[1]=='Y'){
            sum+=6
        }
        if(it[0]=='B' && it[1]=='Z'){
            sum+=6
        }
        if(it[0]=='Z' && it[1]=='X'){
            sum+=6
        }
        if(it[0]=='A' && it[1]=='X' || it[0]=='B' && it[1]=='Y' || it[0]=='C' && it[1]=='Z' ){
            sum+=3
        }
    }

    fun part1(input:List<String>):Int{

        fun shapeScore(shape: Char)=(shape - 'X' + 1)
        fun resultScore(theirShape:Char,myShape:Char):Int{
        return when (theirShape to myShape) {
            'B' to 'X', 'C' to 'Y', 'A' to 'Z' -> 0
            'A' to 'X', 'B' to 'Y', 'C' to 'Z' -> 3
            'C' to 'X', 'A' to 'Y', 'B' to 'Z' -> 6
            else -> error("check your inputs")
        }
        }

        return input.sumOf { round ->
            val(theirShape,myShape) = round
            resultScore(theirShape,myShape)+shapeScore(myShape)
        }
    }

    fun part2(input:List<String>):List<String>{
        return input.map {
                it:String ->
                when(it[1]){
                    'Y' -> when (it[0]) {
                        'A'-> "AX"
                        'B'-> "BY"
                        'C'-> "CZ" else -> throw IllegalStateException("input inválido")
                    }
                    'X' -> when (it[0]) {
                        'A'-> "AZ"
                        'B'-> "BX"
                        'C'-> "CY"
                        else -> throw IllegalStateException("input inválido")
                    }
                    'Z' -> when(it[0]){
                        'A'->"AY"
                        'B'->"BZ"
                        'C'->"CX"
                        else -> throw IllegalStateException("input inválido")
                    }
                    else -> throw IllegalStateException("input inválido")
                }
        }

    }

    val result = part1(part2(listTwo))
    println(list)
}
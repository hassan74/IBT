package com.unifi.ibt.data

import com.unifi.ibt.data.repo.TestHtml

fun main(args: Array<String>) {


    println("TestTest")
    //val numbers = listOf("one", "two", "three", "four", "five")
    //println(numbers.groupBy { it.first()})

/*        val numbers = listOf("one", "two", "three", "four")
        val longerThan3 = numbers.filter { it.length > 3 }
        println(longerThan3)

        val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
        val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10}
        println(filteredMap)

        val html ="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "<h1>My First Heading</h1>\n" +
                "<p>My first paragraph.</p>\n" +
                "</body>\n" +
                "</html>"
        html.replace("<","")
        val pattern ="\\s\\w+\\s".toRegex()
        val res =pattern.findAll(html)
        res.forEach { re-> println(re.value) }*/

    val content = """
Foxes are omnivorous mammals belonging to several genera
of the family Canidae. Foxes have a flattened skull, upright triangular ears,
a pointed, slightly upturned snout, and a long bushy tail. Foxes live on every
continent except Antarctica. By far the most common and widespread species of
fox is the red fox."""

    val pattern = "\\w+".toRegex()

    val words = pattern.findAll(content)
    val count = words.count()

    //  println("There are $count words")

//        words.forEach { matchResult ->
//            println(matchResult.value)
//        }

    var res = "<script src=\"runtime-es2015.6b2268b78dd45a134bd0.js\" type=\"module\"></script>"
        .replace("<script .+</script>".toRegex(), " ")
    println(res)


    println( TestHtml().getHtml()
        .replace("<script (.)*?</script>".toRegex(), " ")
        .replace("<(.|\\n)*?>|</(.)*?>".toRegex(), " ")
    )
}

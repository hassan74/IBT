package com.unifi.ibt.data.ui

import com.unifi.ibt.data.models.Word

internal fun parseHTML(response: String?): ArrayList<Word> {
    val parsedHtml = ArrayList<Word>()
    response
        //Get text inside body TAG
        ?.substringAfter("<body>")
        ?.substringBeforeLast("</body>")
        ?.lowercase()
        //Remove Script TAGS
        ?.replace("<script (.|\n)*?</script>".toRegex(), " ")
        //Remove ALL TAGS
        ?.replace("<(.|\n)*?>|</(.|\n)*?>".toRegex(), " ")
        //Remove special chars
        ?.replace("[^a-zA-Z1-9']".toRegex(), " ")
        ?.trim()
        //Chars with a space before/after it.
        ?.split(" ")
        ?.filter { it.isNotEmpty() }
        ?.groupingBy { it }
        ?.eachCount()
        ?.map {
            parsedHtml.add(Word(it.key, it.value))
        }
    return parsedHtml
}


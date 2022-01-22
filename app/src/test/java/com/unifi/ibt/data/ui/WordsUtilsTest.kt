package com.unifi.ibt.data.ui

import org.junit.Test

import org.junit.Assert.*

class WordsUtilsTest {

    @Test
    fun parsHTML_Return20() {
        //Color text and another color, and now back to the same. Oh, and here's a different background color just in case you need it!
        var htmlTest1 ="<p style=\"color:#B22222\">Color text and <span style=\"color:limegreen;\">another color</span>, and now back to the same. Oh, and here's a <span style=\"background-color:PaleGreen;\">different background color</span> just in case you need it!</p"
        val result =parseHTML(htmlTest1)
        assertEquals(result.size, 20 )
    }
}
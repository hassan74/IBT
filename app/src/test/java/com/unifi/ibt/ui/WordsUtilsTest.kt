package com.unifi.ibt.ui

import com.unifi.ibt.models.Word
import org.junit.Test

import org.junit.Assert.*

class WordsUtilsTest {

    @Test
    fun parsHTML_Returns20() {
        //Color text and another color, and now back to the same. Oh, and here's a different background color just in case you need it!
        val htmlTest1 ="<p style=\"color:#B22222\">Color text and <span style=\"color:limegreen;\">another color</span>, and now back to the same. Oh, and here's a <span style=\"background-color:PaleGreen;\">different background color</span> just in case you need it!</p>"
        val result =parseHTML(htmlTest1)
        assertEquals(result.size, 20 )
        assert(result.contains(Word("and",3)))
        assert(result.contains(Word("color",3)))
    }
    @Test
    fun parsHTML_empty_Returns0() {
        //Color text and another color, and now back to the same. Oh, and here's a different background color just in case you need it!
        val htmlTest2 ="<p style=\"color:" +
                "#B22222\">" +
                "<span style=\"color:limegreen;\"> " +
                "</span>, <span style=\"background-color:PaleGreen;\"></span> !</p>"
        val result =parseHTML(htmlTest2)
        assertEquals(result.size, 0 )
        assert(result.isEmpty())
    }
    @Test
    fun parsHTML_null_Returns0() {
        val htmlTest3 =null
        val result =parseHTML(htmlTest3)
        assertEquals(result.size, 0 )
    }
    @Test
    fun parsHTML_one_Returns1() {
        val htmlTest4 ="one One ONE"
        val result =parseHTML(htmlTest4)
        assertEquals(result.size, 1 )
    }
    @Test
    fun parsHTML_two_Returns2() {
        val htmlTest5 ="one's One"
        val result =parseHTML(htmlTest5)
        assertEquals(result.size, 2 )
    }
}
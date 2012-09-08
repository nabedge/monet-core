package org.mixer2.monet.core.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class AntPathMatcherTest {

    @Test
    public void testMatches() {
        AntPathMatcher matcher = new AntPathMatcher();
        assertTrue(matcher.matches("/**/*.html", "/zzz.html"));
        assertFalse(matcher.matches("/**/*.html", "zzz.html"));
        assertTrue(matcher.matches("/**/*.html", "/foo/hoge/bar/zzz.html"));
        assertFalse(matcher.matches("/**/*.html", "foo/hoge/bar/zzz.html"));
        assertFalse(matcher.matches("/foo/**/bar/*.html", "/foo/hoge/bar/zzz.png"));
    }

    @Test
    public void testMatch() {
        AntPathMatcher matcher = new AntPathMatcher();

        assertTrue(matcher.match("/**/*.html", "/zzz.html"));
        assertFalse(matcher.match("/**/*.html", "zzz.html"));
        assertTrue(matcher.match("/**/*.html", "/foo/hoge/bar/zzz.html"));
        assertFalse(matcher.match("/**/*.html", "foo/hoge/bar/zzz.html"));
        assertTrue(matcher.match("/foo/**/bar/*.html", "/foo/hoge/bar/zzz.html"));
        assertFalse(matcher.match("/foo/**/bar/*.html", "/foo/hoge/bar/zzz.png"));


        assertTrue(matcher.match("/foo/bar.html","/foo/bar.html"));
        assertFalse(matcher.match("/foo/bar.html","/foo/barrrrr.html"));
        assertTrue(matcher.match("/foo/bar","/foo/bar"));
        assertFalse(matcher.match("/foo/bar","/foo/bar/"));
        assertTrue(matcher.match("/foo/bar/*","/foo/bar/zzz"));
        assertFalse(matcher.match("/foo/bar/*","/foo/bar/xxx/yyy"));
        assertTrue(matcher.match("/foo/bar/**","/foo/bar/xxx/yyy"));
        assertFalse(matcher.match("/foo/bar/*","/foo/bar"));
        assertTrue(matcher.match("/foo/*/bar","/foo/xxx/bar"));
        assertFalse(matcher.match("/foo/*/bar","/foo/xxx/yyy/bar"));
        assertTrue(matcher.match("/foo/**/bar","/foo/xxx/yyy/bar"));
        assertTrue(matcher.match("/foo/**/bar","/foo/bar"));
    }

}

/*
 * This is free and unencumbered software released into the public domain.
 * 
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * 
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * For more information, please refer to <http://unlicense.org/>
 */
package org.decampo.examples.functional;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author ray@decampo.org
 */
public class StringJoin {
    // A map to contain some data for us
    private final SortedMap<String,Object> data = new TreeMap<>();

    public SortedMap<String, Object> getData() {
        return data;
    }

    public void putData(Map<String, Object> data) {
        this.data.putAll(data);
    }


    /**
     * Stringify the Java 7 way with StringBuilder.
     * @return string representation
     */
    public String oldToString() {
        final StringBuilder builder = new StringBuilder("Flimflam map { ");
        for (final Map.Entry<String,Object> entry : data.entrySet()) {
            builder.append("flim: ")
                .append(entry.getKey())
                .append(", flam: ")
                .append(entry.getValue())
                .append("; ");
        }
        builder.append('}');
        return builder.toString();
    }

    /**
     * Stringify with functional methods in a naive manner.
     * @return string representation
     */
    public String naiveToString() {
        return data.entrySet().stream()
            .map(entry ->
                "flim: " + entry.getKey() 
                    + ", flam: " + entry.getValue() + "; ")
            .collect(Collectors.joining("", "Flimflam map { ", "}"));
    }

    /**
     * Stringify in a functional manner avoiding extra intermediate strings.
     * @return string representation
     */
    public String smarterToString() {
        return data.entrySet().stream()
            .flatMap(entry -> Stream.of(
                "flim: ", String.valueOf(entry.getKey()),
                ", flam: ", String.valueOf(entry.getValue()), "; "))
            .collect(Collectors.joining("", "Flimflam map { ", "}"));
    }
}

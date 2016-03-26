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
package org.decampo.examples.collections;

import java.util.Arrays;
import java.util.HashSet;
import org.apache.commons.collections4.SetValuedMap;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author ray@decampo.org
 */
public class MoreCollectorsTest
{
    private static String[][] DATA = {
        {"red", "orange", "yellow", "green", "blue", "violet"},
        {"george", "john", "thomas", "james", "james", "john", "andrew"},
        {"one"},
        {"mercury", "venus", "mars", "juno"}
    };
    
    @Test
    public void toSetValuedMapTest() throws Exception {
        // Make a set valued map where the key is the first element in each 
        // array and the set contains all the elements of the array
        final SetValuedMap<String,String> result = Arrays.stream(DATA)
            // convert to arrays of length two
            .flatMap(arr -> Arrays.stream(arr).map(s -> new String[] { arr[0], s }))
            // Now collect the 2-element arrays into a map
            .collect(MoreCollectors.toSetValuedMap(
                arr -> arr[0], 
                arr -> arr[1]));
        
        // Now we verify 
        Arrays.stream(DATA)
            .forEach(arr -> {
                Assert.assertEquals(
                    // a set containing the elements of the array
                    new HashSet<>(Arrays.asList(arr)),
                    // equals the set corresponding to the first element
                    result.get(arr[0]));
            });
    }
}

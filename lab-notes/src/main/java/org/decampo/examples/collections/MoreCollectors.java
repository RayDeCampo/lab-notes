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

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import org.apache.commons.collections4.SetValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

/**
 *
 * @author ray@decampo.org
 */
public class MoreCollectors
{
    public static <T,K,V> Collector<T, SetValuedMap<K,V>, SetValuedMap<K,V>>
        toSetValuedMap(Function<T,K> keyMapper, Function<T,V> valueMapper) {

        return Collector.of(
            HashSetValuedHashMap::new, 
            (map, t) -> map.put(keyMapper.apply(t), valueMapper.apply(t)), 
            (map1, map2) -> { map1.putAll(map2); return map1; },
            Collector.Characteristics.IDENTITY_FINISH,
            Collector.Characteristics.UNORDERED);
    }
        
    public static <T,K> Collector<T, SetValuedMap<K,T>, SetValuedMap<K,T>> 
        groupingByDistinct(Function<T,K> classifier) {
        
        return toSetValuedMap(classifier, Function.identity());
    }
}
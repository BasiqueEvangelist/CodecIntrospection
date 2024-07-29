package me.basiqueevangelist.codecintrospection.element.codec;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;

public interface CodecIntrospectionElement extends IntrospectionElement {
    Codec<?> original();
}

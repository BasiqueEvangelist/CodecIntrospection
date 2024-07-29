package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.Codec;

public interface CodecIntrospectionElement extends IntrospectionElement {
    Codec<?> original();
}

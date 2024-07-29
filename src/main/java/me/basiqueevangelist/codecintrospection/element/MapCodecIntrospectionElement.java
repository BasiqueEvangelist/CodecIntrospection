package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.MapCodec;

public interface MapCodecIntrospectionElement extends IntrospectionElement {
    MapCodec<?> original();
}

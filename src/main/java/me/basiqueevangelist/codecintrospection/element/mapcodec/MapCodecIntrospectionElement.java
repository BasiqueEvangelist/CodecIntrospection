package me.basiqueevangelist.codecintrospection.element.mapcodec;

import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;

public interface MapCodecIntrospectionElement extends IntrospectionElement {
    MapCodec<?> original();
}

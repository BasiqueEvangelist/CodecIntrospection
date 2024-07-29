package me.basiqueevangelist.codecintrospection;

import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElements;
import me.basiqueevangelist.codecintrospection.element.mapcodec.MapCodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.mapcodec.UnknownMapCodecElement;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.AssumeMapUnsafeMapCodecAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class MapCodecIntrospection {
    private static final Map<MapCodec<?>, IntrospectionElement> STATIC_ELEMENTS = new IdentityHashMap<>();
    private static final List<Function<MapCodec<?>, @Nullable IntrospectionElement>> CONVERTERS = new ArrayList<>();

    private MapCodecIntrospection() {

    }

    public static void registerStatic(MapCodecIntrospectionElement element) {
        STATIC_ELEMENTS.put(element.original(), element);
    }

    public static void registerConverter(Function<MapCodec<?>, @Nullable IntrospectionElement> converter) {
        CONVERTERS.add(converter);
    }

    public static IntrospectionElement introspect(MapCodec<?> codec) {
        var staticElement = STATIC_ELEMENTS.get(codec);

        if (staticElement != null) return staticElement;

        if (codec instanceof AssumeMapUnsafeMapCodecAccessor assumeMapUnsafe)
            return CodecIntrospection.introspect(assumeMapUnsafe.getVal$codec());

        for (var converter : CONVERTERS) {
            var res = converter.apply(codec);
            if (res != null) return res;
        }

        return new UnknownMapCodecElement(codec);
    }

    static {
        IntrospectionElements.init();
    }
}

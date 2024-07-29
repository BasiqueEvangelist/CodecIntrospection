package me.basiqueevangelist.codecintrospection.element;

import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.MapCodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.codec.dfu.*;
import me.basiqueevangelist.codecintrospection.element.codec.minecraft.RegistryFileElement;
import me.basiqueevangelist.codecintrospection.element.codec.minecraft.HolderSetElement;
import me.basiqueevangelist.codecintrospection.element.codec.minecraft.StringRepresentableEnumElement;
import me.basiqueevangelist.codecintrospection.element.mapcodec.dfu.*;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class IntrospectionElements {
    public static void init() {
        // Triggers <clinit>.
    }

    static {
        //region DFU
        for (PrimitiveCodecElement element : PrimitiveCodecElement.values()) {
            CodecIntrospection.registerStatic(element);
        }

        CodecIntrospection.registerConverter(UnknownMappedCodecElement::fromCodec);
        CodecIntrospection.registerConverter(MapResultCodecElement::fromCodec);
        CodecIntrospection.registerConverter(UnboundedMapElement::fromCodec);
        CodecIntrospection.registerConverter(PromotePartialCodecElement::fromCodec);
        CodecIntrospection.registerConverter(ListCodecElement::fromCodec);
        CodecIntrospection.registerConverter(EitherCodecElement::fromCodec);

        MapCodecIntrospection.registerConverter(UnknownMappedMapCodecElement::fromMapCodec);
        MapCodecIntrospection.registerConverter(RecordElement::fromMapCodec);
        MapCodecIntrospection.registerConverter(FieldElement::fromMapCodec);
        MapCodecIntrospection.registerConverter(OptionalFieldElement::fromMapCodec);
        MapCodecIntrospection.registerConverter(MapResultMapCodecElement::fromMapCodec);
        MapCodecIntrospection.registerConverter(SimpleMapElement::fromMapCodec);
        MapCodecIntrospection.registerConverter(KeyDispatchElement::fromMapCodec);
        MapCodecIntrospection.registerConverter(UnitElement::fromMapCodec);
        //endregion

        //region Minecraft
        CodecIntrospection.registerConverter(RegistryFileElement::fromCodec);
        CodecIntrospection.registerConverter(HolderSetElement::fromCodec);
        CodecIntrospection.registerConverter(StringRepresentableEnumElement::fromCodec);
        //endregion
    }
}

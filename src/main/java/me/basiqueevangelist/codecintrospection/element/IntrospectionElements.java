package me.basiqueevangelist.codecintrospection.element;

import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.codec.dfu.*;
import me.basiqueevangelist.codecintrospection.element.codec.minecraft.RegistryElementElement;
import me.basiqueevangelist.codecintrospection.element.codec.minecraft.RegistryEntryListElement;
import me.basiqueevangelist.codecintrospection.element.codec.minecraft.StringIdentifiableEnumElement;
import me.basiqueevangelist.codecintrospection.element.mapcodec.dfu.*;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class IntrospectionElements {
    public static void init() {
        //region DFU
        for (PrimitiveCodecElement element : PrimitiveCodecElement.values()) {
            CodecIntrospection.registerStatic(element);
        }

        CodecIntrospection.registerCodecConverter(UnknownMappedCodecElement::fromCodec);
        CodecIntrospection.registerCodecConverter(MapResultCodecElement::fromCodec);
        CodecIntrospection.registerCodecConverter(UnboundedMapElement::fromCodec);
        CodecIntrospection.registerCodecConverter(PromotePartialCodecElement::fromCodec);
        CodecIntrospection.registerCodecConverter(ListCodecElement::fromCodec);

        CodecIntrospection.registerMapCodecConverter(UnknownMappedMapCodecElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(RecordElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(FieldElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(OptionalFieldElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(MapResultMapCodecElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(SimpleMapElement::fromMapCodec);
        CodecIntrospection.registerMapCodecConverter(KeyDispatchElement::fromMapCodec);
        //endregion

        //region Minecraft
        CodecIntrospection.registerCodecConverter(RegistryElementElement::fromCodec);
        CodecIntrospection.registerCodecConverter(RegistryEntryListElement::fromCodec);
        CodecIntrospection.registerCodecConverter(StringIdentifiableEnumElement::fromCodec);
        //endregion
    }
}

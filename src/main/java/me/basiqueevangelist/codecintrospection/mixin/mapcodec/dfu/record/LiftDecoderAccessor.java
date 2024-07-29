// Name taken from https://github.com/haykam821/Caricodec/blob/master/core/src/main/java/io/github/haykam821/caricodec/mixin/LiftDecoderAccessor.java.

package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.record;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.mojang.serialization.codecs.RecordCodecBuilder;

@Mixin(targets = "com.mojang.serialization.codecs.RecordCodecBuilder$Instance$1", remap = false)
public interface LiftDecoderAccessor {
    @Accessor
    RecordCodecBuilder<?, ?> getVal$a();
}
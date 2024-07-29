package me.basiqueevangelist.codecintrospection.testmod;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import org.slf4j.LoggerFactory;

public class CodecIntrospectionTestmod implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();

        demo("Codec.STRING", Codec.STRING);
        demo("ResourceLocation.CODEC", ResourceLocation.CODEC);
        demo("ItemStack.CODEC", ItemStack.CODEC);
        demo("Biome.DIRECT_CODEC", Biome.DIRECT_CODEC);
        demo("BiomeSpecialEffects.CODEC", BiomeSpecialEffects.CODEC);
        demo("ItemEnchantments.CODEC", ItemEnchantments.CODEC);

        System.exit(0);
    }

    private void demo(String name, Codec<?> codec) {
        LoggerFactory.getLogger("CodecIntrospectionTestmod").info("{} => \n{}", name, CodecIntrospection.introspect(codec).dumpToString());
    }
}

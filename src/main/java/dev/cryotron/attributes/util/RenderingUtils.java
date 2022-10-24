package dev.cryotron.attributes.util;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Nullable;

import org.lwjgl.system.MemoryUtil;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.renderer.RenderType;

public class RenderingUtils {
    public static void draw( VertexFormat.Mode drawMode, VertexFormat format, Consumer<BufferBuilder> fn) {
        draw(drawMode, format, bufferBuilder -> {
            fn.accept(bufferBuilder);
            return null;
        });
    }

    public static <R> R draw( VertexFormat.Mode drawMode, VertexFormat format, Function<BufferBuilder, R> fn) {
        BufferBuilder buf = Tesselator.getInstance().getBuilder();
        buf.begin(drawMode, format);
        R result = fn.apply(buf);
        finishDrawing(buf);
        return result;
    }
    
    public static void finishDrawing(BufferBuilder buf) {
        finishDrawing(buf, null);
    }
    
    public static void finishDrawing(BufferBuilder buf, @Nullable RenderType type) {
    	boolean isBuilding = buf.building();
        if (isBuilding) {      
        	if (type != null) {
                type.end(buf, 0, 0, 0);
            } else {
                BufferUploader.draw(buf.end());
            }
        }
    }

}

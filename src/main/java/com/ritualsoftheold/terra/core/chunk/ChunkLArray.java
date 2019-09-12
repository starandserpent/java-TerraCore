package com.ritualsoftheold.terra.core.chunk;

import com.ritualsoftheold.terra.core.DataConstants;
import com.ritualsoftheold.terra.core.markers.Marker;
import com.ritualsoftheold.terra.core.markers.Type;
import com.ritualsoftheold.terra.core.materials.Registry;
import com.ritualsoftheold.terra.core.materials.TerraObject;
import xerial.larray.LByteArray;
import xerial.larray.japi.LArrayJ;

public class ChunkLArray extends Marker {
    private boolean isDifferent;
    private Registry reg;

    public static int CHUNK_SIZE = DataConstants.CHUNK_MAX_BLOCKS;
    private LByteArray chunkVoxelData;

    public ChunkLArray(float x, float y, float z, Registry reg){
        this(x, y, z, LArrayJ.newLByteArray(CHUNK_SIZE), reg);
    }

    public ChunkLArray(float x, float y, float z, LByteArray chunkVoxelData, Registry reg) {
        super(x, y, z, Type.CHUNK);
        this.reg = reg;
        this.chunkVoxelData = chunkVoxelData;
    }

    public byte get(int x, int y, int z){
        int idx = x + (y * CHUNK_SIZE) + (z * CHUNK_SIZE * CHUNK_SIZE);
        return chunkVoxelData.apply(idx);

    }

    public TerraObject get (int idx){
        return reg.getForWorldId((int)chunkVoxelData.getByte(idx));
    }

    public void set(int x, int y, int z, byte data){
        int idx = x + CHUNK_SIZE * (y + CHUNK_SIZE * z);
        chunkVoxelData.update(idx, data);
    }
    public void set(int idx, byte data){
        chunkVoxelData.update(idx, data);
    }

    public LByteArray getChunkVoxelData() {
        return chunkVoxelData;
    }

    public void free(){
        chunkVoxelData.free();
    }

    public void setDifferent(boolean different){
        isDifferent = different;
    }

    public boolean isDifferent() {
        return isDifferent;
    }
}

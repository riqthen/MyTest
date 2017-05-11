package com.riq.mylibrary.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.SparseIntArray;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by riq on 2017/5/11.
 * 播放音频工具
 * 1. 加载音频池 SoundPlayUtils.getInstance().init(this,int[] raws);
 * 2. 播放某音频 SoundPlayUtils.getInstance().playSound(this, rawId)
 */

public class SoundPlayUtils {
    // TODO: 将所有音频放入raws数组中
    private static SoundPool soundPool;
    private static boolean soundPoolLoaded;
    private static SparseIntArray soundIds;

    public static SoundPlayUtils getInstance() {
        return new SoundPlayUtils();
    }

    public void init(final Context context, final int[] raws) {
        soundIds = new SparseIntArray();
        //音频池没有加载的话,则加载线程池
        if (!soundPoolLoaded) {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0); // 同时播放的流的最大数量／流的类型，一般为STREAM_MUSIC／采样率转化质量，当前无效果，使用0作为默认值
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int raw : raws) {
                        //加载音频资源  priority参数目前没有效果，建议设置为1
                        int soundId = soundPool.load(context, raw, 1);  //记载成功将返回一个非0的soundID ，用于播放时指定特定的音频。
                        soundIds.put(raw, soundId);
                    }
                    soundPoolLoaded = true;
                }
            }).start();
        }
    }

    /**
     * 播放音频
     *
     * @param context this
     * @param rawId   R.raw.
     */
    public void playSound(Context context, int rawId) {
        if (soundPoolLoaded) {
            AudioManager am = (AudioManager) context.getSystemService(AUDIO_SERVICE);
            float volume = (float) am.getStreamVolume(AudioManager.STREAM_MUSIC)
                    / am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            Lcat.print(soundIds.get(rawId));
            soundPool.play(soundIds.get(rawId), volume, volume, 1, 0, 1);   //soundId,通过load方法获取／左声道音量比／右声道音量比／优先级,0为最小／
        }
    }

    /**
     * 播放音频
     *
     * @param context  this
     * @param rawId    R.raw.
     * @param priority 流的优先级，值越大优先级高，影响当同时播放数量超出了最大支持数时SoundPool对该流的处理；
     * @param loop     循环次数,负数表示无限循环,0表示播放1次,1表示播放2次,即循环1次
     * @param rate     播放速率[0.5, 2]
     */
    public void playSound(Context context, int rawId, int priority, int loop, float rate) {
        if (soundPoolLoaded) {
            AudioManager am = (AudioManager) context.getSystemService(AUDIO_SERVICE);
            float volume = (float) am.getStreamVolume(AudioManager.STREAM_MUSIC)
                    / am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            Lcat.print(soundIds.get(rawId));
            soundPool.play(soundIds.get(rawId), volume, volume, priority, loop, rate);   //soundId,通过load方法获取／左声道音量比／右声道音量比／优先级,0为最小／
        }
    }

}

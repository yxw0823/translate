package com.framework.core.utils;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;

import java.io.File;

public class ReadVideo
{

    public static void main(String[] args)
    {
        videoEncodeMp4();
        // videoEncode();
    }

    public static void videoEncodeMp4()
    {
        File source = new File("E:\\csmm.mov");
        File target = new File("E:\\csmm.mp4");
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libfaac");
        audio.setBitRate(new Integer(64000));
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(22050));
        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(new Integer(160000));
        video.setFrameRate(new Integer(15));
        video.setSize(new VideoSize(400, 300));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp4");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        try
        {
            encoder.encode(source, target, attrs);
        }

        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void videoEncode3gp()
    {
        File source = new File("E:\\Y4\\[科学巨匠.尼古拉·特斯拉\\target.flv");
        File target = new File("E:\\Y4\\[科学巨匠.尼古拉·特斯拉\\test.3gp");
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libfaac");
        audio.setBitRate(new Integer(128000));
        audio.setSamplingRate(new Integer(44100));
        audio.setChannels(new Integer(2));
        VideoAttributes video = new VideoAttributes();
        video.setCodec("mpeg4");
        video.setBitRate(new Integer(160000));
        video.setFrameRate(new Integer(15));
        video.setSize(new VideoSize(176, 144));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("3gp");
        attrs.setAudioAttributes(audio);
        attrs.setVideoAttributes(video);
        Encoder encoder = new Encoder();
        try
        {
            encoder.encode(source, target, attrs);
        }

        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
package com.framework.core.utils;

import java.net.InetAddress;
import java.util.Date;
import java.util.UUID;

public class UUIDUtils
{
    private String sep = "";
    private static final int IP;
    private static short counter = (short) 0;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

    public static int bytes2Int(byte[] bytes)
    {
        int result = 0;
        for (int i = 0; i < 4; i++)
        {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

    static
    {
        int ipadd;
        try
        {
            ipadd = bytes2Int(InetAddress.getLocalHost().getAddress());
        }
        catch (Exception e)
        {
            ipadd = 0;
        }
        IP = ipadd;
    }

    /**
     * Unique across JVMs on this machine (unless they load this class in the
     * same quater second - very unlikely)
     */
    protected int getJVM()
    {
        return JVM;
    }

    /**
     * Unique in a millisecond for this JVM instance (unless there are >
     * Short.MAX_VALUE instances created in a millisecond)
     */
    protected short getCount()
    {
        synchronized (UUIDUtils.class)
        {
            if (counter < 0)
                counter = 0;
            return counter++;
        }
    }

    /**
     * Unique in a local network
     */
    protected int getIP()
    {
        return IP;
    }

    /**
     * Unique down to millisecond
     */
    protected short getHiTime()
    {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    protected int getLoTime()
    {
        return (int) System.currentTimeMillis();
    }

    protected String format(int shortval)
    {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        int start = 0;
        if (formatted.length() >= 4)
        {
            start = 8 - formatted.length();
        }
        else
        {
            start = 4 - formatted.length();
        }
        buf.replace(start, 4, formatted);
        return buf.toString();
    }

    protected String generateUUID()
    {
        return new StringBuffer(36).append(format(getIP())).append(sep).append(format(getJVM())).append(sep).append(
                format(getHiTime())).append(sep).append(format(getLoTime())).append(sep).append(format(getCount()))
                .toString();
    }

    public static String generateNanoUUID()
    {
        long namo = System.nanoTime();
        return String.valueOf(namo);
    }

    public static String generateMillisUUID()
    {
        long mill = System.currentTimeMillis();
        return String.valueOf(mill).substring(4);
    }

    public static void main(String[] args)
    {
        System.out.println(System.currentTimeMillis());
        System.out.println(generateMillisUUID());
        System.out.println(DateUtils.getDateTime(new Date(System.currentTimeMillis())));
        System.out.println(DateUtils.getDateTime(new Date(System.nanoTime())));
        Integer i = Integer.valueOf(generateMillisUUID().substring(4));
        System.out.println(UUID.randomUUID().getLeastSignificantBits() * -1);
        System.out.println(UUID.randomUUID().getMostSignificantBits());
    }
}

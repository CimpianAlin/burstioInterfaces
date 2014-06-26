package burstio;

import java.util.List;

import org.apache.log4j.Logger;

import org.ossie.properties.AnyUtils;

public final class Utils
{
    private Utils ()
    {
    }

    public static BULKIO.PrecisionUTCTime now ()
    {
        long current = System.nanoTime();
        double wsec = (double)(current / 1000000000);
        double fsec = (current - wsec) * 1e-9;
        return new BULKIO.PrecisionUTCTime(BULKIO.TCM_CPU.value, BULKIO.TCS_VALID.value, 0.0, wsec, fsec);
    }

    public static BURSTIO.BurstSRI createSRI (final String streamID)
    {
        return Utils.createSRI(streamID, 1.0);
    }

    public static BURSTIO.BurstSRI createSRI (final String streamID, double xdelta)
    {
        BURSTIO.BurstSRI sri = new BURSTIO.BurstSRI();
        sri.hversion = 1;
        sri.streamID = streamID;
        sri.id = "";
        sri.xdelta = xdelta;
        sri.mode = (short)0;
        sri.flags = (short)0;
        sri.tau = 0.0;
        sri.theta = 0.0f;
        sri.gain = 0.0f;
        sri.uwlength = (short)0;
        sri.bursttype = (short)0;
        sri.burstLength = 0;
        sri.CHAN_RF = 0.0;
        sri.baudestimate = 0.0f;
        sri.carrieroffset = 0.0;
        sri.SNR = 0.0;
        sri.modulation = "";
        sri.baudrate = 0.0;
        sri.fec = "";
        sri.fecrate = "";
        sri.randomizer = "";
        sri.overhead = "";
        sri.expectedStartOfBurstTime = new BULKIO.PrecisionUTCTime();
        sri.keywords = new CF.DataType[0];
        return sri;
    }

    public static double elapsed (final BULKIO.PrecisionUTCTime begin)
    {
        return Utils.elapsed(begin, Utils.now());
    }

    public static double elapsed (final BULKIO.PrecisionUTCTime begin, final BULKIO.PrecisionUTCTime end)
    {
        return (end.twsec - begin.twsec) + (end.tfsec - begin.tfsec);
    }

    public static Logger getChildLogger(final Logger parent, final String name)
    {
        return Logger.getLogger(parent.getName() + "." + name);
    }

    public static void addKeyword (List<CF.DataType> keywords, final String name, final int value)
    {
        keywords.add(new CF.DataType(name, AnyUtils.toAny(value, "long")));
    }

    public static void addKeyword (List<CF.DataType> keywords, final String name, final double value)
    {
        keywords.add(new CF.DataType(name, AnyUtils.toAny(value, "double")));
    }
}

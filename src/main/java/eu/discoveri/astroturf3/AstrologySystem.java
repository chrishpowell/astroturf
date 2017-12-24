/*
 * Class for astrology systems
 */
package eu.discoveri.astroturf3;

import java.util.List;

/**
 *
 * @author chrispowell
 */
public class AstrologySystem
{
    private final int id;
    private final String str;
    private final List<ZodiacSign> zs;

    public AstrologySystem(int id, String str, List<ZodiacSign> zs)
    {
        this.id = id;
        this.str = str;
        this.zs = zs;
    }

    public int getId() { return id; }

    public String getStr() { return str; }

    public List<ZodiacSign> getZs() { return zs; }
}

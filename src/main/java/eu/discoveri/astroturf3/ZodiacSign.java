/*
 */
package eu.discoveri.astroturf3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chrispowell
 */
public class ZodiacSign
{
    private int id;
    private String str;
    private static List<ZodiacSign> preBuiltSignList = createSignsList();

    public ZodiacSign(int id, String str)
    {
        this.id = id;
        this.str = str;
    }
    
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getStr() { return str; }

    public void setStr(String str) { this.str = str; }
    
    public String outputZodiacSign()
    {
        return id+ ": " +str;
    }
    
    public static List<ZodiacSign> getSignsList()
    {
        return preBuiltSignList;
    }
    
    private static List<ZodiacSign> createSignsList()
    {
        List<ZodiacSign> signs = new ArrayList<>();
        
        signs.add(new ZodiacSign(1,"Cancer"));
        signs.add(new ZodiacSign(2,"Libra"));
        signs.add(new ZodiacSign(3,"Capricorn"));
        signs.add(new ZodiacSign(4,"Pisces"));
        signs.add(new ZodiacSign(5,"Aries"));
        
        return signs;
    }
}

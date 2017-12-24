/*
 */
package eu.discoveri.astroturf3;

/**
 *
 * @author chrispowell
 */
public class ZodiacSign
{
    private int id;
    private String str;

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
}

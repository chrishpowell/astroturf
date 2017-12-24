/*
 */
package eu.discoveri.astroturf3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author chrispowell
 */
public class GQTestLambdas
{
    private List<ZodiacSign> buildWesternSignList()
    {
        ArrayList<ZodiacSign> alzs = new ArrayList<>();
        
        alzs.add(new ZodiacSign(1, "Aries"));
        alzs.add(new ZodiacSign(2, "Taurus"));
        alzs.add(new ZodiacSign(3, "Gemini"));
        alzs.add(new ZodiacSign(4, "Cancer"));
        alzs.add(new ZodiacSign(5, "Leo"));
        alzs.add(new ZodiacSign(6, "Virgo"));
        alzs.add(new ZodiacSign(7, "Libra"));
        alzs.add(new ZodiacSign(8, "Scorpio"));
        alzs.add(new ZodiacSign(9, "Pisces"));
        alzs.add(new ZodiacSign(10, "Cetus"));
        
        return alzs;
    }
    
    private List<ZodiacSign> buildChineseSignList()
    {
        ArrayList<ZodiacSign> alzs = new ArrayList<>();
        
        alzs.add(new ZodiacSign(1, "Aries"));
        alzs.add(new ZodiacSign(2, "Taurus"));
        alzs.add(new ZodiacSign(3, "Gemini"));
        alzs.add(new ZodiacSign(4, "Cancer"));
        alzs.add(new ZodiacSign(5, "Leo"));
        alzs.add(new ZodiacSign(6, "Virgo"));
        alzs.add(new ZodiacSign(7, "Libra"));
        alzs.add(new ZodiacSign(8, "Scorpio"));
        alzs.add(new ZodiacSign(9, "Pisces"));
        alzs.add(new ZodiacSign(10, "Chinese"));
        
        return alzs;
    }
    
    private List<ZodiacSign> buildExtendedSignList()
    {
        ArrayList<ZodiacSign> alzs = new ArrayList<>();
        
        alzs.add(new ZodiacSign(1, "Aries"));
        alzs.add(new ZodiacSign(2, "Taurus"));
        alzs.add(new ZodiacSign(3, "Gemini"));
        alzs.add(new ZodiacSign(4, "Cancer"));
        alzs.add(new ZodiacSign(5, "Leo"));
        alzs.add(new ZodiacSign(6, "Virgo"));
        alzs.add(new ZodiacSign(7, "Libra"));
        alzs.add(new ZodiacSign(8, "Scorpio"));
        alzs.add(new ZodiacSign(9, "Pisces"));
        alzs.add(new ZodiacSign(10, "Extended"));
        
        return alzs;
    }
        
    private List<AstrologySystem> buildAstrologyList()
    {
        ArrayList<AstrologySystem> as = new ArrayList<>();
        
        as.add(new AstrologySystem(1,"Western",buildWesternSignList()));
        as.add(new AstrologySystem(2,"Chinese",buildChineseSignList()));
        as.add(new AstrologySystem(9,"Extended",buildExtendedSignList()));
        
        return as;
    }
    
    private void printSigns(List<AstrologySystem> astrologies, Predicate<AstrologySystem> tester)
    {
        for( AstrologySystem as: astrologies )
        {
            if( tester.test(as) )
            {
                System.out.println("List for: [" +as.getId()+ "] " +as.getStr());
                for( ZodiacSign zs: as.getZs() )
                {
                    System.out.println(">> [" +zs.getId()+ "] " +zs.getStr());
                }
            }
        }
    }
    
    public static void main(String[] args)
    {
        GQTestLambdas gqTestL = new GQTestLambdas();
        gqTestL.printSigns( gqTestL.buildAstrologyList(), al -> al.getStr() == "Extended" );
    }
}

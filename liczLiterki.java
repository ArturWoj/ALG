
/**
 * Created by artur on 11.04.2017.
 */
import java.util.Arrays;
import java.util.*;

public class liczLiterki {

   static String WynikObliczen ="";

    public void liczLiterkiAlePowoli()
    {


        //aaacsssdfgdsfgaasdfasdssbba
        String nap = new String("arytmetyka");   //
        Map<String, Integer> probaLiczenia = new TreeMap<String, Integer>();

        liczLiterki licz = new liczLiterki();

        probaLiczenia = licz.mapaLiterek(nap);

        System.out.println(" ");

        System.out.println(probaLiczenia);

        WynikObliczen = licz.zwrocString(probaLiczenia).substring(3,10);


    }
    public String getWynikObliczen(){
        return WynikObliczen;
    }

    public String zwrocString(Map mapa){

        return mapa.toString();
    }

    public Map mapaLiterek(String nap)
    {
        Map<String, Integer> lisc = new HashMap<String, Integer>();
        char[] tmp = nap.toCharArray();
        Arrays.sort(tmp);
        nap = new String(tmp);


        int c =1;
        //System.out.println(nap.charAt(0));
        String test ="";

        for(int i =0; i < nap.length()-1; i++)
        {
            //	System.out.println(nap.length()+ " i "+i);

            if(nap.charAt(i) == nap.charAt(i+1))
            {
                c++;
            }
            else// (nap.charAt(i) != nap.charAt(i+1))
            {
                test = test + nap.charAt(i) + c;

                lisc.put(Character.toString(nap.charAt(i)), c);
                c=1;

            }
            if(i == (nap.length()-2))
            {
                lisc.put(Character.toString(nap.charAt(i+1)), c);
                test = test + nap.charAt(i+1) + c;
            }

        }
        System.out.println(test);
        //System.out.println(lisc);
        return lisc;
    }

}

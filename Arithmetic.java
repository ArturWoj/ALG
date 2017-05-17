import java.util.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Arithmetic {
	
	public static void main(String[] args){
		
		
		Map<String, Double> charFreq = new TreeMap<String, Double>();
		ArrayList<ArrayList<Double>> borders = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> border = new ArrayList<Double>(); 
		Map<String, Double> wystepZnaku = new TreeMap<String, Double>(); 
		Double start =0.0;
		Double end =1.0;
		int idx =0;
		
		Set<String> tmps = new TreeSet<String>();
		List<String> sorted;
		
		///
		String koduj = "abc";
		///
		String odkoduj ="";
		
		stringToSet(tmps,koduj);
		sorted = new ArrayList<String>(tmps);
		findProbability(wystepZnaku, koduj);
	
		
	
		System.out.println(charFreq);
		System.out.println("  ");

		
	for(int i = 0; i < koduj.length(); i++){
			
			idx = charToOrder(wystepZnaku, koduj.charAt(i));
			//   System.out.println(idx);
    		border = findBorders(wystepZnaku, start, end);
			start = border.get(idx);
			end = border.get(idx+1);
			
	        borders.add(border);
	        System.out.println(borders.get(i));
	        System.out.println("dol :"+start +"  gora "+end);
	        System.out.println("   ");
		}
		
           odkoduj = decode(start, borders, sorted);
       	System.out.println("Odkodowane "+odkoduj);
        
	}
	
	public static String decode(Double start, ArrayList<ArrayList<Double>> borders,List<String> sorted)
	{
		int j;
		String tmp="";
		for(int i=0; i< borders.size(); i++)
		{
			j=0;
			for (Double db : borders.get(i))
			{
				if( db > start)
				{
					break; 
				}
				j++;
			}
			tmp+= sorted.get(j-1);
		
		}
		return tmp;
	}
	
	public static void stringToSet(Set<String> zbior, String koduj)
	{
		for(int i =0; i<koduj.length(); i++)
		{
			zbior.add(String.valueOf(koduj.charAt(i)));
		}
		
		System.out.println(zbior);
	}
	
	
	public static void findProbability(Map<String, Double> wystepZnaku, String koduj)
	{
		Map<String, Integer> probaLiczenia = new TreeMap<String, Integer>();
		Double tmp = 0.0;
		liczLiterki licz = new liczLiterki();
		probaLiczenia = licz.mapaLiterek(koduj);	
		
		for(Map.Entry<String, Integer> en : probaLiczenia.entrySet())
		{
		   // tmp += 	;
			wystepZnaku.put(en.getKey(),((double)(en.getValue()))/koduj.length() );
		//	System.out.println(((double)(en.getValue()))/koduj.length());
			
		}
		System.out.println(wystepZnaku);
		
		for(Map.Entry<String, Double> en : wystepZnaku.entrySet())
		{
			tmp += en.getValue();
		
			wystepZnaku.put(en.getKey(), tmp);
		//	System.out.println(wystepZnaku+ "   "+en.getKey() + "  wart: "+en.getValue());
			
		}
		System.out.println(wystepZnaku);
	}
	
//	public static char OrderToChar(Map<String, Double> charFreq, int j)
//	{
//		for (int i = 0; j<i ; i++)
//		{
//			charFreq.
//			
//		}
//		
//		for(Map.Entry<String, Double> en: charFreq.entrySet())
//		{
//			if(en.getKey().equals(String.valueOf(a)))
//			{
//				break;
//			}
//		
//		}
//		return a;
//	}
	
	
	
	public static int charToOrder(Map<String, Double> charFreq, char a)
	{
		int i=0;
		for(Map.Entry<String, Double> en: charFreq.entrySet())
		{
			if(en.getKey().equals(String.valueOf(a)))
			{
				break;
			}
			i++;	
		}
		return i;
	}
	
	
	public static ArrayList<Double> findBorders(Map<String, Double> czestotliwosc, Double start, Double end){
		DecimalFormat df = new DecimalFormat("0.000000000000");
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		ArrayList<Double> granica = new ArrayList<Double>();
		Double tmp =0.0;

		
		granica.add(start);
		for(Map.Entry<String, Double> en : czestotliwosc.entrySet())
		{
		//	System.out.println(en + "  " + tmp);
			tmp = start+ (end - start) * en.getValue() ;
			tmp = Double.parseDouble(df.format(tmp).replace(",", "."));
	
			granica.add(tmp);
		}
		
	//	
		return granica;
		
	}
	
	
	
}

import java.util.*;

public class SortujMape {
	static int val1 ;
	static Integer val2 = null;
	static String key1 = "" ; 
	static String key2 ; 
		public static void main(String[] args){
			
	        Map<String, Integer> fullMap = new HashMap<String, Integer>();
	        Map<String, Integer> tmpMap = new HashMap<String, Integer>();
	        Map<String, String> codedMap = new HashMap<String, String>();
	        Map<String, String> codedMapVK= new HashMap<String, String>();
	       
	        Map<Integer, Map<String, Integer>> bitsMap = new HashMap<Integer, Map<String, Integer>>();
	        int sizeOfMap;  
	        
	        
	       // String NapisWejsciowy = new String("abbcccdddd");
	        String NapisWejsciowy = new String("mississippi-river");
	        String NapisZakodowany = new String();
	        String NapisOdkodowany = new String();
	        
	        System.out.println(" Napis Wejœciowy:  "+ NapisWejsciowy);
	        System.out.println("     ");
	        
	        //	        fullMap.put("i", 5);
//	        fullMap.put("s", 4);
//	        fullMap.put("p", 2);
//	        fullMap.put("r", 2);
//	        fullMap.put("m", 1);
//	        fullMap.put("v", 1);
//	        fullMap.put("e", 1);
//	        fullMap.put("-", 1);
	        
	        liczLiterki licz = new liczLiterki();
	        fullMap = licz.mapaLiterek(NapisWejsciowy);
	  
	        sizeOfMap = fullMap.size();
	                      
	   //   System.out.println(fullMap.keySet());
	        System.out.println(fullMap.entrySet());
	        System.out.println("     ");
	         	        	    
	        codedMap = copyKeys(fullMap);


	       for(int i=0; i< sizeOfMap-1; i++){
	    	    tmpMap = new HashMap<String, Integer>(fullMap);
	    	    bitsMap.put(i, tmpMap);
		        findAndRemoveTwoLowestValue(fullMap);
		        fullMap.put(key1,val1);
 		 //     System.out.println("        "+fullMap.entrySet());
		 //     System.out.println("MAPA MAP  "+bitsMap.entrySet());
	       }
	        
	  
//		System.out.println(bitsMap.get(2)+"   do SUBA   " +bitsMap.get(2).size()+ "   cout all " + bitsMap.size() );
	
	// pierwsze; sta³e dodanie bitów
		subKey(codedMap, fullMap, bitsMap.get(sizeOfMap-2));
		
	   	for(int i=sizeOfMap-2; i >= 1; i--){
			
	 //  		System.out.println("WEJSCIE MAPY "+i +"  " +bitsMap.get(i)+ "   I   "+ bitsMap.get(i-1));	
	   		subKey(codedMap, bitsMap.get(i), bitsMap.get(i-1));
		}	 
		
		//System.out.println("#### "+ 	bitsMap.get(1));
		//subKey(codedMap, bitsMap.get(1));

	       System.out.println("     ");	
		   	for(int i=sizeOfMap-2; i >= 0; i--){
				
		   		System.out.println("MAPA "+i +"  " +bitsMap.get(i) );		
			}		
		   	
		   	System.out.println("Zakodowana mapa  "+"  " + codedMap );	
		    NapisZakodowany = zakodujNapis(codedMap, NapisWejsciowy);
		   	System.out.println("Zakodowany wyraz  "+"  " + NapisZakodowany );	
		   	codedMapVK = keyToValue(codedMap);
		   	
	   	
		   	NapisOdkodowany = odkodujNapis(codedMapVK, NapisZakodowany);
		   	
		  	System.out.println("Napis odkodowany  "+"  " + NapisOdkodowany );
		   	
		   	
		}

		public static String odkodujNapis(Map<String, String> codedMapVK, String NapisZakodowany)
		{
			String odkodowany = "";
			String znak = "";
			
			for(int i =0; i < NapisZakodowany.length(); i++)
			{
				znak = znak + NapisZakodowany.charAt(i);
			//	System.out.println(" "+ znak); 
				
				for(Map.Entry<String, String> m : codedMapVK.entrySet())
				{
					if(m.getKey().equals(znak))
					{
				//		System.out.println(" "+ znak + " " +m.getValue()); 
						znak = ""; 
						odkodowany = odkodowany + m.getValue();	
					}
				}
			}	
			return odkodowany;
		}
				
		public static String zakodujNapis(Map<String, String> codedMap, String NapisWejsciowy)
		{
			String zakodowany = "";
			
			for(int i =0; i < NapisWejsciowy.length(); i++)
			{
				System.out.println(" "+ NapisWejsciowy.charAt(i)+"  "+ codedMap.get(String.valueOf(NapisWejsciowy.charAt(i)))); 
				zakodowany = zakodowany + codedMap.get(String.valueOf(NapisWejsciowy.charAt(i)));
			}					   
		   	return zakodowany;
		}
		
		public static Map keyToValue(Map<String, String> codedMap)
		{
			Map tmpMap = new HashMap<String, String>();
			
			for(Map.Entry<String, String> m : codedMap.entrySet())
			{
		      tmpMap.put(m.getValue(),m.getKey());
			}	
			return tmpMap;
		}
		
		
		public static void subKey(Map<String, String> codedMap, Map<String, Integer> MapOne, Map<String, Integer> MapTwo)
		{  
			// Dla ka¿dego elementu countMap  //zamiana na V K 
			int i =0;
	
			// znajdz elementy które do tej port sie nie pojawily (dwa)
			
			for(String KeyOne : MapOne.keySet() )
			{
				for(String KeyTwo : MapTwo.keySet() )
				{
			
				//	System.out.println("#### "+ KeyOne + " " + KeyTwo);
					
					if (KeyOne.equals(KeyTwo))
					{
						MapTwo.put(KeyTwo, 0);
					}
				}
			}
	
			//System.out.println("klucze do wa¿ne " + MapTwo);
			//	addOne(codedMap, Klucz);
			
			for(Map.Entry<String, Integer> cm : MapTwo.entrySet() )
			{
				if(cm.getValue()!= 0)
				{
					if(i ==0){
					//	System.out.println("  Klucz:   "+ cm);	
						addZero(codedMap, cm.getKey());
				
					//	System.out.println("  mapa 1   "+codedMap);
						i++;
					} else{
					//	System.out.println("  Klucz:   "+ cm);	
						addOne(codedMap, cm.getKey());
					//	System.out.println("  mapa 2   "+codedMap);
					}
				}	
			}		   
		}

		public static void addZero(Map<String, String> codedMap, String key)
		{
			String tmp = "";
			
			for(int i =0; i < key.length(); i++)
			{
				tmp = codedMap.get(String.valueOf(key.charAt(i))) + "0";
				
				codedMap.put(String.valueOf(key.charAt(i)), tmp);
			//	System.out.println("Dodaj ZERO "+ codedMap);
			}
		}
		public static void addOne(Map<String, String> codedMap, String key)
		{

			String tmp = "";
			
			for(int i =0; i < key.length(); i++)
			{
				tmp = codedMap.get(String.valueOf(key.charAt(i))) + "1";
				
				codedMap.put(String.valueOf(key.charAt(i)), tmp);
			//	System.out.println("Dodaj JEDEN "+ codedMap);

			}
		}
		
		
	  // kopiuje wszystkei klucze aby pozniej nadaæ im wartoœæ binarn¹	
	   public static Map copyKeys(Map<String, Integer> mainMap)
	   {
		    Map<String, String> codedMap = new HashMap<String, String>();
		   
			for(Map.Entry<String, Integer> tmp : mainMap.entrySet())
			{
				codedMap.put(tmp.getKey(), "");
			}
		   return codedMap;
	   }
				
		
        public static void findAndRemoveTwoLowestValue(Map<String, Integer> fullMap){
    		key1= "";
    		val1 =0;
        	for(int i =0; i<2; i++){
        		Integer min = Collections.min(fullMap.values());
	        	String K = null;
	        	String klucz = null;
	        	Integer V = null;
	        	
	            for(Map.Entry<String, Integer> entry : fullMap.entrySet())
		        {
		        	K = entry.getKey();
		        	V = entry.getValue();
		        	
		        	if(Objects.equals(min, V))
		        	{
		       // 		System.out.println("Klucz  -"+ K + "-  minimalnego V :" +min +"  " +V); 
		        		 klucz = K;
		        		 break;
		        	}	        	
		        //	System.out.println(K + "  para  "+ V + "  min "+ min);
		        }
	            
		        key1 = key1 + klucz;
		        val1 = val1 + fullMap.get(klucz);
		 //       System.out.println("key   "+ key1+ " val   "+ val1);
		        fullMap.remove(klucz);
        	}
	
        }
		
}



//fullMap.put("t", 1);
//fullMap.put("e", 1);
//fullMap.put("a", 1);
//fullMap.put("s", 1);
//fullMap.put("m", 2);
//fullMap.put("n", 1);
//fullMap.put("i", 1);
//fullMap.put("z", 2);
//


//fullMap.put("i", 8);
//fullMap.put("s", 7);
//fullMap.put("p", 6);
//fullMap.put("r", 5);
//fullMap.put("m", 4);
//fullMap.put("v", 3);
//fullMap.put("e", 2);
//fullMap.put("-", 1);






//  Znaj³dz dwie najmniejsze Values z mapy

//      min = Collections.min(fullMap.values());

//     keyToDel = findAndRemoveLowestValue(min,fullMap);


//  System.out.println("  tmpMap "+ tmpMap);



//    keyToDel = findAndRemoveLowestValue(min2,fullMap);
//key2 = keyToDel;
//val2 = fullMap.get(key2);
//System.out.println("key 2  "+ key2+ " val 2  "+ val2);
//fullMap.remove(keyToDel);
//
//
//keyToDel = findAndRemoveLowestValue(min,fullMap);

//     System.out.println("  klucz do kasowania  "+ findAndRemoveLowestValue(min,fullMap)+ " " +min + "  "+ fullMap);






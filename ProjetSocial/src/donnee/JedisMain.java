package donnee;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import modele.Salon;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisMain 
{
	  //address of your redis server
    private static final String redisHost = "127.0.0.1";
    private static final Integer redisPort = 6379;
    private static final String key = "salons";
    //the jedis connection pool..
    private static JedisPool pool = null;
    
    public JedisMain() 
    {
        pool = new JedisPool(redisHost, redisPort);
        addSets();
    }
 
    public void addSets() 
    {
    	List<Salon> listeSalons = SalonDAO.getInstance().getListe();
    	
    	
        String salon1 = "";
        String salon2 = "";
        String salon3 = "";
        
    	for(Salon salon:listeSalons) 
        {	
    		switch(salon.getId())
    		{
    		case 1:
    			salon1 = salon.getTitre();
    			break;
    		case 2:
    			salon2 = salon.getTitre();
    			break;
    		case 3:
    			salon3 = salon.getTitre();
    			
    		}
    		
        }
    	
    	Jedis jedis = pool.getResource();
        jedis.sadd(key, salon1, salon2, salon3);
        
        
    }
    
    public List<String> getCache()
    {
    	List<String> listSalons = new ArrayList<String>();
    	Jedis jedis = pool.getResource();
    	Set<String> salons = jedis.smembers(key);
        for (String salon : salons) 
        {
        	System.out.println(salon);
            listSalons.add(salon);
        }
        
       return listSalons;
    }
}

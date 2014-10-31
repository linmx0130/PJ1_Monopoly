/** NormalLand
 *  class of normal land
 */
package monopoly.kernel.land;
import java.util.Scanner;
import monopoly.kernel.*;

public class NormalLand extends AbstractLand
{
	//for details, please turn to document 2.3.2
	public String name;
	public int owner;
	public int basicPrice;
	public int level;
	public int street;
	public NormalLand(MapManager mapM, int typeId, 
					String name, int basicPrice, int street)
	{
		super(mapM,typeId);
		this.name=name;
		this.owner=-1;
		this.basicPrice=basicPrice;
		this.level=1;
		this.street=street;
	}
	//getBuyingPrice
	//it will return the price for buying this land
	public int getBuyingPrice()
	{
		return level*basicPrice;
	}
	//getArrivedPrice
	//it will return the price for arriving at it
	public int getArrivedPrice()
	{
		//TODO
		return (int)(level*basicPrice*0.3);
	}
	public void arrivedAction(int userId)
	{
		//TODO
	}
	public void passingAction(int userId){}
	public static AbstractLand loader(MapManager mapM, Scanner fin)
	{
		String name=fin.next();
		int basicPrice=fin.nextInt();
		int street=fin.nextInt();
		NormalLand ret=new NormalLand(mapM,2,name,basicPrice,street);
		return ret;
	}
}

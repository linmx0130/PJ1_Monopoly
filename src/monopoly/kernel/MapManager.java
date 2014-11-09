/** MapManager
 *  The unit to manage the actions of moving.
 */

package monopoly.kernel;
import java.io.File;
import java.util.Scanner;
import monopoly.kernel.land.*;
import monopoly.ui.MessageManager;
public class MapManager
{
	public int unitTotal;
	public int sizeX,sizeY;
	public AbstractLand[] unitList;
	public int[][] position;
	public int[] userPosition;
	//the directions of users
	//1 is positive direction and -1 is negative direction
	public int[] userDirection;
	//used to control dice value
	public int nextDiceValue=0;
	MapManager()
	{
		this.unitTotal=0;
	}
	/** getDistance
	 *  get the distance between two users
	 */
	public int getDistance(int user1,int user2)
	{
		if (userPosition[user1]>userPosition[user2])
		{
			int k=user1;
			user1=user2;
			user2=k;
		}
		int ret=userPosition[user2]-userPosition[user1];
		if (userPosition[user1]+unitTotal-userPosition[user2]< ret)
		{
			ret=userPosition[user1]+unitTotal-userPosition[user2];
		}
		return ret;
	}
	/** userWalk
	 *  ask user to walk for step
	 */
	public void userWalk(int userId, int step)
	{
		if (nextDiceValue!=0)
		{
			System.out.println("遥控骰子卡效果影响，本次骰子的值为"+nextDiceValue+"。");
			step=nextDiceValue;
			nextDiceValue=0;
		}
		if (MainController.userList[userId].beTurtle) 
		{
			MessageManager.showMessage(MessageManager.MESSAGE,"MapManager",
					"受龟速卡效果影响，"+MainController.userList[userId].getName()+
					"只能走一步！");
			step=1;
		}
		MessageManager.showMessage(MessageManager.MESSAGE,"User",
						MainController.userList[userId].getName()+"走了"+step+"步！");
		for (int i=1;i<=step;++i)
		{
			userPosition[userId]+=userDirection[userId];
			if (userPosition[userId]>=unitTotal) userPosition[userId]-=unitTotal;
			if (userPosition[userId]<0) userPosition[userId]+=unitTotal;
			unitList[userPosition[userId]].passingAction(userId);
		}
		unitList[userPosition[userId]].arrivedAction(userId);
	}
	/** loadMap: load map data from file.
	 */
	public void loadMap(String fileName) throws Exception
	{
		Scanner fin=new Scanner(new File(fileName));
		unitTotal=fin.nextInt();
		sizeX=fin.nextInt();
		sizeY=fin.nextInt();
		unitList=new AbstractLand[unitTotal];
		position=new int[unitTotal][2];
		for (int i=0;i<unitTotal;++i)
		{
			int typeId=fin.nextInt();
			position[i][0]=fin.nextInt();
			position[i][1]=fin.nextInt();
			switch (typeId)
			{
				case 1: //EmptyLand
					unitList[i]=EmptyLand.loader(this,fin,i);
					break;
				case 2: //NormalLand
					unitList[i]=NormalLand.loader(this,fin,i);
					break;
				case 3: //BankSite
					unitList[i]=BankSite.loader(this,fin,i);
					break;
				case 4: //NewsSite
					unitList[i]=NewsSite.loader(this,fin,i);
					break;
				case 5: //LotterySite
					unitList[i]=LotterySite.loader(this,fin,i);
					break;
				case 6: //FreeCardSpot
					unitList[i]=FreeCardSpot.loader(this,fin,i);
					break;
				case 7: //CouponSite
					unitList[i]=CouponSite.loader(this,fin,i);
					break;
				case 8: //CardShop
					unitList[i]=CardShop.loader(this,fin,i);
					break;
				default:
					LogManager.log(LogManager.ERROR,"MapManager",
								"Unknow type id of land: "+typeId);
					System.exit(1);
			}
		}
		fin.close();
	};
};

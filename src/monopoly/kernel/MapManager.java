/** MapManager
 *  The unit to manage the actions of moving.
 */

package monopoly.kernel;
import java.io.File;
import java.util.Scanner;
import monopoly.kernel.land.*;

public class MapManager
{
	public int unitTotal;
	public int sizeX,sizeY;
	public AbstractLand[] unitList;
	public int[][] position;
	public int[] userPosition;
	MapManager()
	{
		this.unitTotal=0;
	}
	/** userWalk
	 *  ask user to walk for step
	 */
	public void userWalk(int userId, int step)
	{
		for (int i=1;i<=step;++i)
		{
			++userPosition[userId];
			if (userPosition[userId]>=unitTotal) userPosition[userId]-=unitTotal;
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
				default:
					LogManager.log(LogManager.ERROR,"MapManager",
								"Unknow type id of land: "+typeId);
					System.exit(1);
			}
		}
		fin.close();
	};
};

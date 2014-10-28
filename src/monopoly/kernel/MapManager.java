/** MapManager
 *  The unit to manage the actions of moving.
 */

package monopoly.kernel;
import java.io.File;
import java.util.Scanner;
import monopoly.kernel.land.*;

public class MapManager
{
	private int unitTotal;
	private int sizeX,sizeY;
	private AbstractLand[] unitList;
	private int[][] position;
	MapManager()
	{
		this.unitTotal=0;
	}
	
	/** loadMap: load map data from file.
	 */
	void loadMap(String fileName) throws Exception
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
					unitList[i]=EmptyLand.loader(this,fin);
					break;
				case 2: //NormalLand
					unitList[i]=NormalLand.loader(this,fin);
					break;
				default:
					LogManager.log(LogManager.ERROR,"MapManager",
								"Unknow type id of land.");
					System.exit(1);
			}
		}
		fin.close();
	};
};

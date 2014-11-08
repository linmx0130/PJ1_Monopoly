/** MapViewer
 *  Used to show the map
 */
package monopoly.ui;
import monopoly.kernel.*;
import monopoly.kernel.land.*;
public class MapViewer
{
	private final static int landKindTotal=8;
	private final static String[] userMark={"α ","β ","γ ","δ ","ε ","ζ ","η ","θ "};
	private final static String[] userLandMark={"⓪ ","① ","② ","③ ","④ ","⑤ ","⑥ ","⑦ ","⑧ "};
	private final static String[] landMark=
	{"","空","◎ ","￥","新","彩","卡","券","道"};
	/**showBasicMap
	*  show map without user mark
	*/
	public static void showBasicMap()
	{
		MapManager map=MainController.map;
		String buffer[][]=new String[map.sizeX][map.sizeY];
		for (int i=0;i<map.sizeX;++i)
		{
			for (int j=0;j<map.sizeY;++j)
			{
				buffer[i][j]="  ";
			}
		}
		for (int i=0;i<map.unitList.length;++i)
		{
			AbstractLand nowUnit=map.unitList[i];
			int x=map.position[i][0]-1;
			int y=map.position[i][1]-1;
			if (nowUnit.typeId<=landKindTotal)
			{
				buffer[x][y]=landMark[nowUnit.typeId];
			}else
			{
					LogManager.log(LogManager.PANIC,"UI::MapViewer","Illegal unit on map!");
					System.exit(1);
			}
		}
		for (int i=0;i<map.sizeX;++i)
		{
			for (int j=0;j<map.sizeY;++j)
			{
				System.out.print(buffer[i][j]+" ");
			}
			System.out.println();
		}
	}
	/** showNowMap
	 *  show the map with user marks
	 */
	public static void showNowMap(int userId)
	{
		MapManager map=MainController.map;
		String buffer[][]=new String[map.sizeX][map.sizeY];
		for (int i=0;i<map.sizeX;++i)
		{
			for (int j=0;j<map.sizeY;++j)
			{
				buffer[i][j]="  ";
			}
		}
		for (int i=0;i<map.unitList.length;++i)
		{
			AbstractLand nowUnit=map.unitList[i];
			int x=map.position[i][0]-1;
			int y=map.position[i][1]-1;
			if (nowUnit.typeId<=landKindTotal)
			{
				buffer[x][y]=landMark[nowUnit.typeId];
			}
			else
			{
				LogManager.log(LogManager.PANIC,"UI::MapViewer","Illegal unit on map!");
				System.exit(1);
			}
			if (nowUnit.typeId==2)
			if ( ((NormalLand)nowUnit).owner==-1) buffer[x][y]=landMark[2];
			else buffer[x][y]=userLandMark[((NormalLand)nowUnit).owner];
		}
		for (int i=0;i<MainController.userTotal;++i)
		{
			int x=map.position[map.userPosition[i]][0]-1;
			int y=map.position[map.userPosition[i]][1]-1;
			if (MainController.inGame[i]) buffer[x][y]=userMark[i];
		}
		int x=map.position[map.userPosition[userId]][0]-1;
		int y=map.position[map.userPosition[userId]][1]-1;
		buffer[x][y]=userMark[userId];
		for (int i=0;i<map.sizeX;++i)
		{
			for (int j=0;j<map.sizeY;++j)
			{
				System.out.print(buffer[i][j]+" ");
			}
			System.out.println();
		}
	}

};

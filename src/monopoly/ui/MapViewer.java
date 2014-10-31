/** MapViewer
 *  Used to show the map
 */
package monopoly.ui;
import monopoly.kernel.*;
import monopoly.kernel.land.*;
public class MapViewer
{
	private final static String[] userMark={"α ","β ","γ ","δ ","ε ","ζ ","η ","θ "};
	private final static String[] userLandMark={"⓪ ","① ","② ","③ ","④ ","⑤ ","⑥ ","⑦ ","⑧ "};
	private final static String normalLandMark="◎ ";
	private final static String emptyLandMark="空";
	private final static String bankSiteMark="￥";
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
			switch (nowUnit.typeId)
			{
				case 1: //empty land
					buffer[x][y]=emptyLandMark;
					break;
				case 2: //normal land
					buffer[x][y]=normalLandMark;
					break;
				case 3: //bank site
					buffer[x][y]=bankSiteMark;
					break;
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
	public static void showNowMap()
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
			switch (nowUnit.typeId)
			{
				case 1: //empty land
					buffer[x][y]=emptyLandMark;
					break;
				case 2: //normal land
					buffer[x][y]=normalLandMark;
					break;
				case 3: //bank site
					buffer[x][y]=bankSiteMark;
					break;
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

};

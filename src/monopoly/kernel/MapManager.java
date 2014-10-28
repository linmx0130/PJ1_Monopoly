/** MapManager
 *  The unit to manage the actions of moving.
 */

package monopoly.kernel;
import monopoly.kernel.AbstractLand;
import java.io.File;
import java.util.Scanner;

public class MapManager
{
	private int unitTotal;
	private AbstractLand unitList[];
	MapManager()
	{
		this.unitTotal=0;
	}
	
	/** loadMap: load map data from file.
	 */
	void loadMap(String fileName) throws Exception
	{
		Scanner fin=new Scanner(new File(fileName));
		fin.close();
	};
};

/** AbstractLand
 *  Provided the abstract class of all land units.
 */
package monopoly.kernel.land;
import java.util.Scanner;
import monopoly.kernel.*;

public abstract class AbstractLand
{
	//typeId: the type id of this kind of land
	public int typeId;
	// mapM: the reference of map manager
	private MapManager mapM;
	// landId: the id in map
	public int landId;
	public AbstractLand(MapManager mapM, int typeId, int landId)
	{
		this.typeId=typeId;
		this.mapM=mapM;
		this.landId=landId;
	}
	/**
	 * arrived Action: the action when a user arrvied at this land.
	 */
	public abstract void arrivedAction(int userId);
	/**
	 * passingAction: involked when a user passing the land.
	 */
	public abstract void passingAction(int userId);
	/* PAY ATTENTION!
	 * All land classes should implentment loader(MapManager mapM, Scanner fin, int landId) method 
	 */
};

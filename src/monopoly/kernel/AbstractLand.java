/** AbstractLand
 *  Provided the abstract class of all land units.
 */
package monopoly.kernel;
import java.util.Scanner;

public abstract class AbstractLand
{
	//typeId: the type id of this kind of land
	public int typeId;
	// mapM: the reference of map manager
	private MapManager mapM;
	public AbstractLand(MapManager mapM, int typeId)
	{
		this.typeId=typeId;
		this.mapM=mapM;
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
	 * All land classes should implentment loader(MapManager mapM, Scanner fin) method 
	 */
};

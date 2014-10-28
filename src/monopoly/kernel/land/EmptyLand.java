/** EmptyLand
 *  the empty land, really empty :)
 */
package monopoly.kernel.land;
import java.util.Scanner;
import monopoly.kernel.*;
public class EmptyLand extends AbstractLand
{
	public EmptyLand(MapManager mapM)
	{
		super(mapM,1);
	}
	public void arrivedAction(int userId) {};
	public void passingAction(int userId) {};
	public static AbstractLand loader(MapManager mapM, Scanner fin)
	{
		EmptyLand ret=new EmptyLand(mapM);
		return ret;
	}
};

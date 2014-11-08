/** BankSite
 *  the land for bank
 */
package monopoly.kernel.land;
import java.util.Scanner;
import monopoly.kernel.*;
public class BankSite extends AbstractLand
{
	private BankSite(MapManager mapM,int landId)
	{
		super(mapM,3,landId);
	}
	public void arrivedAction(int userId) {};
	public void passingAction(int userId) 
	{
		monopoly.kernel.BankSystem.bankSiteAction(userId);
	};
	public static AbstractLand loader(MapManager mapM, Scanner fin,int landId)
	{
		BankSite ret=new BankSite(mapM,landId);
		return ret;
	}
};

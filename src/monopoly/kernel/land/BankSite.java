/** BankSite
 *  the land for bank
 */
package monopoly.kernel.land;
import java.util.Scanner;
import monopoly.kernel.*;
public class BankSite extends AbstractLand
{
	public BankSite(MapManager mapM)
	{
		super(mapM,3);
	}
	public void arrivedAction(int userId) {};
	public void passingAction(int userId) 
	{
		monopoly.kernel.BankSystem.bankSiteAction(userId);
	};
	public static AbstractLand loader(MapManager mapM, Scanner fin)
	{
		BankSite ret=new BankSite(mapM);
		return ret;
	}
};

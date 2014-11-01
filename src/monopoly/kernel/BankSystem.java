/** BankSystem
 *  the logic code of bank system
 */

package monopoly.kernel;
import monopoly.ui.*;

public class BankSystem
{
	//the rate of interest per month
	private final static double interestRate=0.01;
	//savings of users
	public static int[] savings;
	/* store(int userId,int amount)
	 * user stores some money
	 * return false means there are no enough cash
	 */
	public static boolean store(int userId, int amount)
	{
		if (MainController.userList[userId].getCash()<amount)
		{
			//refuse the request
			return false;
		}
		MainController.userList[userId].modifyCash(-amount);
		savings[userId]+=amount;
		return true;
	}
	/* withdraw (int userId,int amount)
	 * user withdraw some money
	 * return false means there are no enough money in the account
	 */
	public static boolean withdraw(int userId, int amount)
	{
		if (savings[userId]<amount)
		{
			//refuse the request
			return false;
		}
		MainController.userList[userId].modifyCash(amount);
		savings[userId]-=amount;
		return true;
	}
	// generateInterest
	// put interest into users' accounts
	public static void generateInterest()
	{
		for (int i=0;i<MainController.userTotal;++i)
		{
			savings[i]+=(int)interestRate*savings[i];
		}
	}
	// bankSiteAction
	// involked when users passing the bank
	public static void bankSiteAction(int userId)
	{
		BankForm.showBankForm(userId);
	}
}

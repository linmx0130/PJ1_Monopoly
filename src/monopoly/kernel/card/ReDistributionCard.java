/** ReDistributionCard
 *  redistribute all cash
 */
package monopoly.kernel.card;
import monopoly.kernel.MainController;
public class ReDistributionCard extends AbstractCard
{
	public void endEffectAction(){};
	public boolean cardQuestion(){return true;};
	public void effectAction()
	{
		int totalCash=0;
		for (int i=0;i<MainController.userTotal;++i)
		{
			totalCash+=MainController.userList[i].getCash();
		}
		int onesCash=totalCash/MainController.userTotal;
		for (int i=0;i<MainController.userTotal;++i)
		{
			MainController.userList[i].modifyCash(onesCash-MainController.userList[i].getCash());
		}
	}
	public ReDistributionCard (int subjectUserId)
	{
		super("均富卡",8,0,subjectUserId,-1);
	}
}

/** StayANightCard
 *  stay at the place a night
 */
package monopoly.kernel.card;
import monopoly.kernel.MainController;
public class StayANightCard extends AbstractCard
{
	public void effectAction()
	{
		MainController.userList[this.subjectUserId].stayANight=true;
	}
	public StayANightCard (int subjectUserId)
	{
		super("滞留卡",0,0,subjectUserId,-1);
	}
}

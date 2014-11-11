/** TaxCard
 *  ask other users to pay tax
 */
package monopoly.kernel.card;
import monopoly.kernel.MainController;
import monopoly.kernel.BankSystem;

public class TaxCard extends AbstractCard
{
	public boolean cardQuestion(){return true;}
	public void endEffectAction(){}
	public void effectAction()
	{
		BankSystem.savings[objectUserId]-=(int)(BankSystem.savings[objectUserId]*0.3);
	}
	public TaxCard(int subjectUserId, int objectUserId)
	{
		super("查税卡",6,0,subjectUserId,objectUserId);
	}
};


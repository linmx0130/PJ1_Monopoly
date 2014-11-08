/** CardShop
 *  the shop to selling cards
 */
package monopoly.kernel.land;
import java.util.Scanner;
import monopoly.kernel.*;
import monopoly.ui.CardShopForm;
public class CardShop extends AbstractLand
{
	private CardShop(MapManager mapM,int landId)
	{
		super(mapM,8,landId);
	}
	public void arrivedAction(int userId) 
	{
		CardShopForm.showCardShopForm(userId);
	}
	public void passingAction(int userId) {};
	public static AbstractLand loader(MapManager mapM, Scanner fin,int landId)
	{
		CardShop ret=new CardShop(mapM,landId);
		return ret;
	}
};

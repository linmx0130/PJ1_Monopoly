/** MonopolyGame 
 *  The entrance of the game.
 */
import monopoly.kernel.MainController;
public class MonopolyGame
{
	public static void main(String[] args) throws Exception
	{
		String mapFileName=args[0];
		MainController.mainLoop(mapFileName);
	}
};

/** MessageManager
 *  Provided message dialogs and message store.
 */
package monopoly.ui;
import monopoly.kernel.LogManager;
public class MessageManager
{
	// normal message
	public final static int MESSAGE=1;
	// some user operation is illegal
	public final static int WARNING=2;
	// program error
	public final static int ERROR=3;
	// showMessage
	// print the message and store the message(TODO)
	public static void showMessage(int typeId,String source, String message)
	{
		String buffer="";
		switch (typeId)
		{
			case MessageManager.MESSAGE:
				break;
			case MessageManager.WARNING:
				buffer="【警告】";
				break;
			case MessageManager.ERROR:
				buffer="【错误】";
				break;
			default:
			LogManager.log(LogManager.ERROR,"MessageManager","Illegal arguments!");
		}
		buffer+=message;
		System.out.println(buffer);
		//store the message (TODO)
	}
};

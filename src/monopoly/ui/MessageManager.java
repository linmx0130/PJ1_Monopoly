/** MessageManager
 *  Provided message dialogs and message store.
 */
package monopoly.ui;
import monopoly.kernel.LogManager;
public class MessageManager
{
	// normal message
	public final int MESSAGE=1;
	// some user operation is illegal
	public final int WARNING=2;
	// program error
	public final int ERROR=3;
	// showMessage
	// print the message and store the message(TODO)
	public static void showMessage(int typeId,String source String message)
	{
		String buffer;
		switch (typeId)
		{
			case MESSAGE:
				break;
			case WARNING:
				buffer="【警告】";
				break;
			case ERROR:
				buffer="【错误】";
				break;
			default:
			LogManager.log("MessageManager","Illegal arguments!");
		}
		buffer+=message;
		System.out.println(buffer);
		//store the message (TODO)
	}
};

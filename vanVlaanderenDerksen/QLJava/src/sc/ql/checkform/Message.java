package sc.ql.checkform;

public class Message {
	private final MessageType type;
	private final String message;
	private final String position;
	
	public enum MessageType {
		ERROR, WARNING;
	}
	
	public Message(MessageType type, String message, String position) {
		this.type = type;
		this.message = message;
		this.position = position;
	}
	
	public String getType() {
		return this.type.toString();
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getPosition() {
		return this.position;
	}
	
	public String toString() {
		return this.getType()+" on line "+this.getPosition()+" "+this.getMessage();
	}
}

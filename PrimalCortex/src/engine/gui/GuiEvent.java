package engine.gui;

import java.util.EventObject;

public class GuiEvent extends EventObject {
	
	private String parentId;
	private String objectId;
	private EventActionType eat;
	
	public GuiEvent(Object source, String parentId, String objectId, EventActionType eat) {
		super(source);		
		this.parentId = parentId;
		this.objectId = objectId;
		this.eat = eat;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public String getObjectId() {
		return objectId;
	}
	
	public EventActionType getEventActionType() {
		return eat;
	}

}

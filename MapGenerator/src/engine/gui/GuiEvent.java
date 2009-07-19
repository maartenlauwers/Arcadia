package engine.gui;

import java.util.EventObject;

public class GuiEvent extends EventObject {
	
	private String parentId;
	private String objectId;
	
	public GuiEvent(Object source, String parentId, String objectId) {
		super(source);		
		this.parentId = parentId;
		this.objectId = objectId;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public String getObjectId() {
		return objectId;
	}
	

}

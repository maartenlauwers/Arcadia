package game.roles;

public abstract class Role {

	private String roleName;
	
	public Role(String roleName) {
		this.roleName = roleName;
	}
	
	public String getName() {
		return roleName;
	}
	
	public void setName(String roleName) {
		this.roleName = roleName;
	}
	
	public String toString() {
		return roleName;
	}
}

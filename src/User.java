public	class User{
	private String name;
	private int points; 
	private boolean moderator;
	
	public User(String name, int points, boolean moderator) {
		super();
		this.name = name;
		this.points = points;
		this.moderator = moderator;
	}
	
	public User(String name){
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isModerator() {
		return moderator;
	}

	public void setModerator() {
		this.moderator = true;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
	
	
}		
		

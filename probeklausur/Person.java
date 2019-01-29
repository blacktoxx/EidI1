public class Person{

	private String name;
	private String surname;
	private int age;

	private Person[] friends;


	public static void main(String[] args) {
		
	}

	public Person(String name, String surname, int age){

		this.name = name;
		this.surname = surname;
		this.age = age;

		friends = new Person[0];
	}

	@Override
	public String toString(){

		String out = "Name: " + name + " " + surname + ", Alter: " + age;
		return out;

	}

	@Override
	public boolean equals(Object o){

		if (!(o instanceof Person)) {
			return false;
		}
		return equals((Person) o);
	}

	public boolean equals(Person p){
		if (p == null) {
			return false;
		}
		if (!name.equals(p.getName())) {
			return false;
		}else if (!surname.equals(p.getSurname())) {
			return false;
		}else if (age != p.getAge()) {
			return false;
		}
		return true;
	}

	public void addFriend(Person p){
		if (p == null || isFriendsWith(p)) {
			return;
		}
		Person[] newFriends = new Person[friends.length + 1];

		for (int i = 0; i < friends.length; i++) {
			newFriends[i] = friends[i];
		}
		newFriends[friends.length] = p;
		friends = newFriends;

		if (!p.isFriendsWith(this)) {
			p.addFriend(this);
		}
	}

	public boolean isFriendsWith(Person p){

		for (Person friend : friends){
			if (p.equals(friend)) {
				return true;
			}
		}
		return false;
	}

	public void listFriends(){

		System.out.println("Freunde von " + toString());
		for (Person friend : friends) {
			System.out.println(friend);
		}
		System.out.println();
	}

	public int connections(Person p){

		int connection = connections(p, Integer.MAX_VALUE, 0);

		return (connection == Integer.MAX_VALUE) ? -1 : connection;
	}

	public int connections(Person p, int min, int level){

		if (min <= level) {
			return min;
		}
		if (equals(p)) {
			return level;
		}

		for (int i = 0; i < friends.length; i++) {
			if (friends[i] == null) {
				continue;
			}

			Person buffer = friends[i];
			friends[i] = null;
			min = buffer.connections(p, min, level + 1);

			friends[i] = buffer;
		}
		return min;


	}

	// Getter und Setter
	public String getName(){
		return name;
	}

	public String getSurname(){
		return surname;
	}

	public int getAge(){
		return age;
	}

}
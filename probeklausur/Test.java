public class Test{

	public static void main(String[] args){

		Person f1 = new Person("Diether", "McBolle", 10);
		Person f2 = new Person("Albert", "Einstein", 11);
		Person f3 = new Person("Duglass", "Hoffer", 100);
		Person f4 = new Person("Janigli", "Andersen", 51);
		Person f5 = new Person("Heinrich", "Himmler", 41);
		Person f6 = new Person("Joseph", "Goebbels", 101);
		Person f7 = new Person("Alberto", "Blanko", 74);
		Person f8 = new Person("Giesbert", "Gärtner", 20);
		Person f9 = new Person("Kalle", "Haudrauf", 38);
		Person f10 = new Person("Norbert", "Haudrauf", 60);

		Person f99 = new Person("Diether", "McBolle", 10);


		// Testfaelle
		System.out.println(f1.equals((Object)f99));

		/*
		f1.addFriend(f2);
		f1.addFriend(f3);
		//f1.listFriends();
		//f2.listFriends();

		f2.addFriend(f3);
		f2.addFriend(f4);
		f2.addFriend(f5);

		f3.addFriend(f2);
		f3.addFriend(f6);
		f3.addFriend(f10);

		f6.addFriend(f1);
		f6.addFriend(f8);
		f9.addFriend(f10);
		f9.addFriend(f7);

		f1.listFriends();
		*/
		f1.addFriend(f2);
		f2.addFriend(f3);
		f3.addFriend(f4);
		f4.addFriend(f5);
		f5.addFriend(f6);

		System.out.println(f1.connections(f6));

	}
}
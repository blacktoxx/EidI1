public class DynamicArray{


	private int[] array;
	private int nextFreeIndex;


	public static void main(String[] args){

		DynamicArray dynArray = new DynamicArray(3);
		dynArray.add(5);
		dynArray.add(7);
		dynArray.add(10);
		dynArray.add(11);
		dynArray.add(14);
		dynArray.add(7);
		dynArray.add(19);
		System.out.println(dynArray);

		dynArray.remove(2);
		System.out.println(dynArray);

		dynArray.resize(6);
		System.out.println(dynArray);

		dynArray.add(1);
		dynArray.add(2);
		dynArray.add(6);
		dynArray.add(1);
		System.out.println(dynArray);

		System.out.println(dynArray.searchAndReplace(1, 100));
		System.out.println(dynArray);

		System.out.println(dynArray.get(1));
		// dynArray.get(-1);
		// Wirft den Fehler!
	}


	public DynamicArray(){
		array = new int[10];
		nextFreeIndex = 0;
	}

	public DynamicArray(int length){
		array = new int[length];
		nextFreeIndex = 0;
	}

	// dieser Konstruktor ist nicht erlaubt

	/*
	public DynamicArray(int[] arrayIn){

		array = new int[arrayIn.length];
		for (int i = 0; i < arrayIn.length; i++) {
			array[i] = arrayIn[i];
		}

		nextFreeIndex = arrayIn.length;

	}
	*/

	public DynamicArray(int... values){

		array = new int[values.length];
		for (int i = 0; i < values.length; ) {
			array[i] = values[i];
		}

		nextFreeIndex = values.length;
	}
	

	public void resize(int newLength){

		// Buffer speichert das 'alte' array
		int[] newArray = new int[newLength];

		// Uebertrag der neuen Werte
		for (int i = 0; i < Math.min(array.length, newLength); i++) {
			newArray[i] = array[i];
		}

		nextFreeIndex = Math.min(array.length, newLength);

		// Auffuellen der Restnullen
		if (newLength > array.length) {
			for (int i = array.length; i < newLength; i++) {
				newArray[i] = 0;
			}
			nextFreeIndex = array.length;
		}
		array = newArray;
	}

	public void add(int val){

		if (nextFreeIndex < array.length) {
			array[nextFreeIndex] = val;
			nextFreeIndex++;
		}else{
			resize(array.length + 1);
			add(val);
		}
	}

	public String toString(){

		String info = "";
		for (int i = 0; i < nextFreeIndex; i++) {
			info += ("\nIndex " + i + ": " + array[i]);
		}
		// freie Indizes werden ebenfalls ausgegeben
		for (int i = nextFreeIndex; i < array.length; i++) {
			info += ("\nIndex " + i + ": frei!");
		}
		return info;
	}

	public void remove(int index){
		if (index >= array.length) {
			return;
		}

		for (int i = index; i < array.length - 1; i++) {
			array[i] = array[i + 1];
		}
		array[array.length - 1] = 0;
		nextFreeIndex--;
	}

	public int get(int index){
		if (index >= array.length || index < 0) {
			throw new ArrayIndexOutOfBoundsException("Der geforderte Index nicht erlaubt!");
		}
		return array[index];
	}

	public int searchAndReplace(int value, int replacement){

		int count = 0;
		for (int i = 0; i < nextFreeIndex; i++) {
			if (array[i] == value) {
				array[i] = replacement;
				count++;
			}
		}
		return count;
	}

}
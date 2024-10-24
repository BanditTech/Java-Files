public class FunctionRecursive {
	public static void main(String[] args) {
		Recursive(10);
	}

	public static void Recursive(int i) {
		if (i < 1)
			return;
		System.out.println(i);
		i -= 1;
		Recursive(i);
	}
}

package FuzzyMatrix;

public class Test {
	public static void main(String[] args)
	{
		double [][] mat = {{1, 0.1, 0.2}, {0.1, 1, 0.3}, {0.2, 0.3, 1}};
		FuzzyMatrix matrix = new FuzzyMatrix(mat);
		
		FuzzyMatrix closure = matrix.getTransitiveClosure();
		closure.print();
	}
}

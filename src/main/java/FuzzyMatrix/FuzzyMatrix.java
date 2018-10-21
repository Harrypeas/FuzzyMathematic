package FuzzyMatrix;

/**
 * 
 * @author Harrypeas
 *
 */
public class FuzzyMatrix {
	private double[][] matrix_data;
	private int row;
	private int col;
	private int matrix_property; //0=unknown 1=common 2=similar 3=equal
	
	/**
	 * 模糊矩阵构造函数，根据传入的数组数据返回相应的矩阵对象
	 * @param matrix_data double类型的二维数组
	 */
	public FuzzyMatrix(double[][] matrix_data) {
		// TODO Auto-generated constructor stub
		this.matrix_data = matrix_data;
		row = matrix_data.length;
		col = matrix_data[0].length;
		matrix_property = 0;
	}
	
	/**
	 * 模糊矩阵构造函数，通过传入行、列数返回一个零矩阵
	 * @param row
	 * @param col
	 */
	public FuzzyMatrix(int row, int col) {
		// TODO Auto-generated constructor stub
		if (row == 0 || col == 0) {
			System.err.println("The row/column should be a non-zero value");
		}
		this.row = row;
		this.col = col;
		matrix_property = 0;
		
		matrix_data = new double[row][col];
		for(int i = 0; i < row; i ++)
		{
			for(int j = 0; j< col; j ++)
			{
				matrix_data[i][j] = 0.0;
			}
		}
	}
	
	/**
	 * 拷贝当前矩阵
	 * @return 与当前模糊矩阵相同的模糊矩阵对象
	 */
	public FuzzyMatrix copy()
	{
		FuzzyMatrix matrix = new FuzzyMatrix(row, col);
		for(int i = 0; i < row; i ++)
		{
			for(int j = 0; j < col; j ++)
			{
				matrix.get_matrix_data()[i][j] = matrix_data[i][j];
			}
		}
		
		return matrix;
	}
	
	/**
	 * 获取当前矩阵的数据
	 * @return 当前矩阵对应的double类型二维数组
	 */
	public double[][] get_matrix_data()
	{
		return matrix_data;
	}
	
	/**
	 * 获取行数
	 * @return 行数
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * 获取列数
	 * @return 列数
	 */
	public int getCol()
	{
		return col;
	}
	
	/**
	 * 传入一个新的double类型的二维数组更新当前矩阵的数据
	 * @param matrix_data double类型二维数组
	 */
	public void updata_matrix_data(double[][] matrix_data)
	{
		this.matrix_data = matrix_data;
		row = matrix_data.length;
		col = matrix_data[0].length;
		matrix_property = 0;
	}
	
	/**
	 * 获取矩阵元素值（还需要修改，当前的下标是从0开始的）
	 * @param i 行号
	 * @param j 列号
	 * @return 矩阵对应行列号的元素值
	 */
	public double get(int i, int j)
	{
		if (i >= row || j >= col) {
			System.err.println("The element is out of bounds");
			return Double.NaN;
		}
		return matrix_data[i][j];
	}
	
	/**
	 * 判断两个矩阵是否相同
	 * @param matrix 用来比较的另一个矩阵
	 * @return true表示两个矩阵相等，false表示不想等
	 */
	public boolean isEqual(FuzzyMatrix matrix)
	{
		for(int i = 0; i < row; i ++)
		{
			for(int j = 0; j < col; j ++)
			{
				if (matrix_data[i][j] != matrix.get(i, j)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 判断一个矩阵是否小于等于（被包含与）另一个矩阵
	 * @param matrix 用来比较的另一个矩阵
	 * @return true表示当前矩阵小于另一个矩阵，false表示不小于或等于
	 */
	public boolean isInclude(FuzzyMatrix matrix)
	{
		for(int i = 0; i < row; i ++)
		{
			for(int j = 0; j < col; j ++)
			{
				if (matrix_data[i][j] > matrix.get(i, j)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 矩阵求交运算
	 * @param matrix 参与运算的另一个矩阵
	 * @return 求交运算结果矩阵
	 */
	public FuzzyMatrix intersect(FuzzyMatrix matrix)
	{
		if (row != matrix.getRow() && col != matrix.getCol()) {
			System.err.println("These two matrices' dimension should be same!");
			return null;
		}
		
		FuzzyMatrix fuzzyMatrix = new FuzzyMatrix(row, col);
		for(int i = 0; i < row; i ++)
		{
			for(int j = 0; j < col; j ++)
			{
				fuzzyMatrix.get_matrix_data()[i][j] = Math.min(matrix_data[i][j], matrix.get(i, j));
			}
		}
		
		return fuzzyMatrix;
	}
	
	/**
	 * 矩阵求并运算
	 * @param matrix 参与运算的另一个矩阵
	 * @return 求并运算结果矩阵
	 */
	public FuzzyMatrix union(FuzzyMatrix matrix)
	{
		if (row != matrix.getRow() && col != matrix.getCol()) {
			System.err.println("These two matrices' dimension should be same!");
			return null;
		}
		
		FuzzyMatrix fuzzyMatrix = new FuzzyMatrix(row, col);
		for(int i = 0; i < row; i ++)
		{
			for(int j = 0; j < col; j ++)
			{
				fuzzyMatrix.get_matrix_data()[i][j] = Math.max(matrix_data[i][j], matrix.get(i, j));
			}
		}
		
		return fuzzyMatrix;
	}
	
	/**
	 * 当前矩阵求补运算
	 * @return 当前矩阵的补集矩阵
	 */
	public FuzzyMatrix complement()
	{
		FuzzyMatrix fuzzyMatrix = new FuzzyMatrix(row, col);
		for(int i = 0; i < row; i ++)
		{
			for(int j = 0; j < col; j ++)
			{
				fuzzyMatrix.get_matrix_data()[i][j] = 1 - matrix_data[i][j];
			}
		}
		
		return fuzzyMatrix;
	}
	
	/**
	 * 模糊矩阵复合运算（与矩阵乘法类似）
	 * @param matrix 参与运算的另一个矩阵
	 * @return 复合运算结果矩阵
	 */
	public FuzzyMatrix composite(FuzzyMatrix matrix)
	{
		if (col != matrix.getRow()) {
			System.err.println("The first matrix's column should be equal to the second matrix's row!");
			return null;
		}
		
		FuzzyMatrix fuzzyMatrix = new FuzzyMatrix(row, matrix.getCol());
		for(int i = 0; i < row; i ++)
		{
			for(int j = 0; j < matrix.getCol(); j ++)
			{
				for(int k = 0; k < col; k ++)
				{
					fuzzyMatrix.get_matrix_data()[i][j] = Math.max(fuzzyMatrix.get(i, j), Math.min(matrix_data[i][k], matrix.get(k, j)));
				}
			}
		}
		
		return fuzzyMatrix;
	}
	
	/**
	 * 对当前矩阵进行转置
	 * @return 转置矩阵
	 */
	public FuzzyMatrix transpose()
	{
		FuzzyMatrix fuzzyMatrix = new FuzzyMatrix(col, row);
		for(int i = 0; i < col; i ++)
		{
			for(int j = 0; j < row; j ++)
			{
				fuzzyMatrix.get_matrix_data()[i][j] = matrix_data[j][i];
			}
		}
		
		return fuzzyMatrix;
	}
	
	/**
	 * 对当前模糊矩阵求lambda-截矩阵
	 * @param lambda lambda值
	 * @return lambda-截矩阵
	 */
	public FuzzyMatrix lambda_mat(double lambda)
	{
		if (lambda > 1 || lambda < 0) {
			System.err.println("Error lambda value! Its value should be within 0 to 1!");
			return null;
		}
		
		FuzzyMatrix fuzzyMatrix = new FuzzyMatrix(row, col);
		for(int i = 0; i < row; i ++)
		{
			for(int j = 0; j < col; j ++)
			{
				fuzzyMatrix.get_matrix_data()[i][j] = matrix_data[i][j] >= lambda ? 1 : 0;
			}
		}
		
		return fuzzyMatrix;
	}
	
	/**
	 * 判断矩阵是否是自反的
	 * @return true表示矩阵为自反矩阵，false表示不是自反矩阵
	 */
	public boolean isReflexivity()
	{
		if (row != col) {
			System.err.println("The matrix should be a square matrix!");
			return false;
		}
		
		for(int i = 0; i < row; i ++)
		{
			if (Math.abs(matrix_data[i][i] - 1) > 1e-8 ) {
				return false;
			}
		}
		
		System.out.println("This matrix is a fuzzy reflexive matrix!");
		return true;
	}
	
	/**
	 * 判断矩阵是否是对称的
	 * @return true表示矩阵是对称矩阵，false表示不是对称矩阵
	 */
	public boolean isSymmetry()
	{
		if (row != col) {
			System.err.println("The matrix should be a square matrix!");
			return false;
		}
		
		for(int i = 0; i < row; i ++)
		{
			for(int j = i; j < col; j ++)
			{
				if (matrix_data[i][j] != matrix_data[j][i]) {
					return false;
				}
			}
		}
		
		System.out.println("This matrix is a fuzzy symmetric matrix!");
		return true;
	}
	
	/**
	 * 判断矩阵是否是传递矩阵
	 * @return true表示矩阵是传递矩阵，false表示不是
	 */
	public boolean isTransitivity()
	{
		if (row != col) {
			System.err.println("The matrix should be a square matrix!");
			return false;
		}
		
		if (composite(this).isInclude(this)) {
			return true;
		}
		
		System.out.println("This matrix is a fuzzy transitive matrix!");
		return false;
	}
	
	/**
	 * 获取矩阵的性质（模糊相等矩阵/模糊相似矩阵/普通模糊矩阵）
	 * @return 3:模糊相等矩阵 2:模糊相似矩阵 1:普通模糊矩阵
	 */
	public int getMatrixPorperty()
	{
		if (matrix_property != 0) {
			return matrix_property;
		}
		
		boolean reflexivity = isReflexivity();
		boolean symmetry = isSymmetry();
		boolean transivity = isTransitivity();
		
		if (reflexivity && symmetry && transivity) {
			matrix_property = 3;
			return matrix_property;
		}
		else if (reflexivity && symmetry && (!transivity)) {
			matrix_property = 2;
			return matrix_property;
		}
		else {
			matrix_property = 1;
			return matrix_property;
		}
	}
	
	/**
	 * 判断该矩阵是否是模糊相似矩阵，如果是的话就求取该模糊相似矩阵的传递闭包
	 * @return 模糊相似矩阵的传递闭包
	 */
	public FuzzyMatrix getTransitiveClosure()
	{
		if (row != col) {
			System.err.println("The matrix should be a square matrix!");
			return null;
		}
		
		if(getMatrixPorperty() == 3)
		{
			return this;
		}
		else if (getMatrixPorperty() == 2) {
			FuzzyMatrix matrix = copy();

			while(true)
			{
				FuzzyMatrix temp = matrix.copy();
				matrix = matrix.composite(matrix);
				if (matrix.isInclude(temp)) {
					return temp;
				}
			}
		}
		else 
		{
			return null;
		}
	}
	
	/**
	 * 打印矩阵
	 */
	public void print()
	{
		for(int i = 0; i < row; i ++)
		{
			for(int j = 0; j < col; j ++)
			{
				System.out.print(matrix_data[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
}

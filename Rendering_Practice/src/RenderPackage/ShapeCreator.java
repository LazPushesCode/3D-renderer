package RenderPackage;


class ShapeCreator{
	public static void renderVertices(int[][] shape) {
		for(int i = 0; i < shape.length; i++) {
			Map.cordMap[shape[i][1]][shape[i][0]] = '.';
		}
	}
	public static void drawLines(int[][] shape) {
		for(int xIndex= 0; xIndex< shape.length-1; xIndex++) {
			//passs in the shape vertices, x calculation, y calculation
			prepareLineCalculations(shape, shape[xIndex+1][0] - shape[xIndex][0], shape[xIndex+1][1] - shape[xIndex][1], xIndex, false);
		}
		prepareLineCalculations(shape, shape[0][0]- shape[shape.length-1][0], shape[0][1]- shape[shape.length-1][1], shape.length-1, true);
	}
	public static void prepareLineCalculations(int[][]shape, int x, int y, int xIndex, boolean wrapper) {
		double m, b;
		m = (double) y/x;
		if(x == 0) m = 0;
		b = shape[xIndex][1] - (shape[xIndex][0] * m);
		
		if(wrapper) {
			wrapper(shape, x, y, xIndex, m, b);
			return;
		}
		
		if(shape[xIndex][0] != shape[xIndex+1][0]) {
			interpolate(shape[xIndex][0], shape[xIndex+1][0], m, b, shape[xIndex][0], false);
			return;
		}
		if(shape[xIndex][1] != shape[xIndex+1][1]) {
			interpolate(shape[xIndex][1], shape[xIndex+1][1], m, b, shape[xIndex][0], true);
			return;
		}
	}
	public static void wrapper(int[][]shape, int x, int y, int xIndex, double m,double b) {
		if(shape[0][0] != shape[xIndex][0]) {
			interpolate(shape[0][0], shape[xIndex][0], m, b, shape[xIndex][0], false);
			return;
		}
		if(shape[0][1] != shape[xIndex][1]) {
			interpolate(shape[0][1], shape[xIndex][1], m, b, shape[xIndex][0], true);
			return;
		}
	}
	
	public static void interpolate(int start, int end, double m, double b, int constant, boolean xConstFlag) {
		for(int i = (start <= end) ? (start+1) : (start-1); i != end; i = (start <= end) ? (i+1) : (i-1)) {
			if(xConstFlag == true) {
				Map.cordMap[i][constant] = '.';
				continue;
			}
			double y = (m*i) + b;
			Map.cordMap[(int)y][(int)i] = '.';
		}
	}
	public static void connectShapes(int[][] shape1, int[][]shape2) {
		for(int xIndex = 0; xIndex < shape1.length; xIndex++) {
			try {
				int line[][] = {
						{shape1[xIndex][0], shape1[xIndex][1]},
						{shape2[xIndex][0], shape2[xIndex][1]}
				};
				prepareLineCalculations(line, line[1][0] - line[0][0], line[1][1] - line[0][1], 0, false);
			}catch(Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
	public static int[][] squarePreset(int x, int y,int length, int width) {
		return new int[][] {
			{x,y},
			{x,y+length},
			{x+width,y+length},
			{x+width,y}
		};
	}
	public static int[][] trianglePreset(int x, int y,int width, int height){
		return new int[][] {
			{x,y},
			{x + (width/2), y+height},
			{x-(width/2), y+height}
		};
	}
	
}

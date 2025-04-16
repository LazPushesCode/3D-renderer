package RenderPackage;


class ShapeCreator{
	public static void main(String[] args) {
		
		char[][] map = new char[100][180];
		
		int shape1[][] = {
				{0,0},
				{20,20},
				{40,0}
		};
		int shape2[][] = {
				{10,10},
				{20,20},
				{50,10}
		};
		int shape3[][] = {
				{20,20},
				{40,20},
				{40,40},
				{20,40}
		};
		int shape4[][] = {
				{40,40},
				{50,40},
				{50,50},
				{40,50}
		};
		//shape 1
		renderVertices(map,shape3);
		drawLines(map, shape3);
		renderVertices(map,shape4);
		drawLines(map,shape4);
		connectShapes(map,shape3,shape4);
		
		
		renderVertices(map,shape1);
		drawLines(map, shape1);
		renderVertices(map, shape2);
		drawLines(map, shape2);
		connectShapes(map, shape1, shape2);
		

		
		//just print the result
		printMap(map);
	}
	public static void renderVertices(char[][] map, int[][] shape) {
		for(int i = 0; i < shape.length; i++) {
			map[shape[i][1]][shape[i][0]] = '.';
		}
	}
	public static void drawLines(char[][]map, int[][] shape) {
		for(int xIndex= 0; xIndex< shape.length-1; xIndex++) {
			//passs in the shape vertices, x calculation, y calculation
			prepareLineCalculations(map, shape, shape[xIndex+1][0] - shape[xIndex][0], shape[xIndex+1][1] - shape[xIndex][1], xIndex, false);
		}
		prepareLineCalculations(map, shape, shape[0][0]- shape[shape.length-1][0], shape[0][1]- shape[shape.length-1][1], shape.length-1, true);
	}
	public static void prepareLineCalculations(char[][]map, int[][]shape, int x, int y, int xIndex, boolean wrapper) {
		double m, b;
		m = (double) y/x;
		if(x == 0) m = 0;
		b = shape[xIndex][1] - (shape[xIndex][0] * m);
		
		if(wrapper) {
			wrapper(map, shape, x, y, xIndex, m, b);
			return;
		}
		
		if(shape[xIndex][0] != shape[xIndex+1][0]) {
			interpolate(map, shape[xIndex][0], shape[xIndex+1][0], m, b, shape[xIndex][0], false);
			return;
		}
		if(shape[xIndex][1] != shape[xIndex+1][1]) {
			interpolate(map, shape[xIndex][1], shape[xIndex+1][1], m, b, shape[xIndex][0], true);
			return;
		}
	}
	public static void wrapper(char[][]map, int[][]shape, int x, int y, int xIndex, double m,double b) {
		if(shape[0][0] != shape[xIndex][0]) {
			interpolate(map, shape[0][0], shape[xIndex][0], m, b, shape[xIndex][0], false);
			return;
		}
		if(shape[0][1] != shape[xIndex][1]) {
			interpolate(map, shape[0][1], shape[xIndex][1], m, b, shape[xIndex][0], true);
			return;
		}
	}
	
	public static void interpolate(char[][]map, int start, int end, double m, double b, int constant, boolean xConstFlag) {
		for(int i = (start <= end) ? (start+1) : (start-1); i != end; i = (start <= end) ? (i+1) : (i-1)) {
			if(xConstFlag == true) {
				map[i][constant] = '.';
				continue;
			}
			double y = (m*i) + b;
				map[(int)y][(int)i] = '.';
		}
	}
	public static void connectShapes(char[][]map, int[][] shape1, int[][]shape2) {
		for(int xIndex = 0; xIndex < shape1.length; xIndex++) {
			try {
				int line[][] = {
						{shape1[xIndex][0], shape1[xIndex][1]},
						{shape2[xIndex][0], shape2[xIndex][1]}
				};
				prepareLineCalculations(map, line, line[1][0] - line[0][0], line[1][1] - line[0][1], 0, false);
			}catch(Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	public static void printMap(char[][]map) {
		String screen = "";
		for(int i = 0; i < 80; i++) {
			for(int j = 0; j < 180; j++) {
				screen += map[i][j];
			}
			screen += "\n";
		}
		System.out.println(screen);
	}
}

package RenderPackage;

public class Map {
	static char[][]cordMap = new char[80][300];
	
	public static void printMap() {
		String screen = "";
		for(int i = 0; i < 80; i++) {
			for(int j = 0; j < 300; j++) {
				screen += cordMap[i][j];
			}
			screen += "\n";
		}
		System.out.println(screen);
	}
}

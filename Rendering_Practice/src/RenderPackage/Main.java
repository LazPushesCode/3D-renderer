package RenderPackage;

public class Main {
	public static void main(String[] args) {
		GameObject preset = new GameObject();
		
		//you can utilize the squarepreset to create square quickly!
		preset.addShape(ShapeCreator.squarePreset(0, 0, 20, 20));
		preset.addShape(ShapeCreator.squarePreset(30, 30, 20, 20));
		//ensure you manually connect shapes to make a 3D shape! (with the index to retrieve their data)
		preset.connectShapes(0, 1);
		
		//same with triangles!
		preset.addShape(ShapeCreator.trianglePreset(70, 0, 20, 10));
		preset.addShape(ShapeCreator.trianglePreset(80, 0, 20, 10));
		preset.connectShapes(2, 3);
		
		GameObject vertices = new GameObject();
		
		//we can manipulate vertices manually and create some unique shapes using addShape()
		vertices.addShape(
				new int[][] {
					{80,15},
					{80,25},
					{100,25}
				}
				);
		//there is also the option to add 2 shapes at a time (useful for creating 3d shapes)
		vertices.addMultipleShapes(
				new int[][] {
					{70, 30},
					{80,30},
					{90,40},
					{60,40}
				},
				new int[][] {
					{80, 35},
					{90,35},
					{100,45},
					{70,45}
				});
		
		//use the connectShapes() function to connect two shapes within the vertices shape list (pass in indexes)
		vertices.connectShapes(1, 2);
		
		
		
		//render all shapes help by the object
		preset.renderObject();
		vertices.renderObject();
		//print the objects to the screen
		Map.printMap();
	}
}

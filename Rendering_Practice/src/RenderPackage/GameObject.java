package RenderPackage;

import java.util.ArrayList;

public class GameObject {
	ArrayList<int[][]> shapes;
	GameObject(){
		shapes = new ArrayList<>();
	}
	public void addMultipleShapes(int[][]shape1, int[][]shape2) {
		shapes.add(shape1);
		shapes.add(shape2);
	}
	public void addShape(int[][]shape) {
		shapes.add(shape);
	}
	public void renderObject() {
		for(int i = 0; i < shapes.size(); i++) {
			try {
			ShapeCreator.renderVertices(shapes.get(i));
			ShapeCreator.drawLines(shapes.get(i));
			}catch(Exception e) {
			}
		}
	}
	public void connectShapes(int index1, int index2) {
		ShapeCreator.connectShapes(shapes.get(index1), shapes.get(index2));
	}
	
	public void trianglePreset() {
		
	}
	
}

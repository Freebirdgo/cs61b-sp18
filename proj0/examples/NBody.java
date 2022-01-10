public class NBody{
	public double ReadRadius(String filename){
		In in = new In(filename);
		int N = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
}
package graph;

public class Maze {

	// throw new IllegalArgumentException();

	private int size;
	private Place start;
	private Place end;
	private Place[][] maze;

	public Maze(int size, int startx, int starty, int endx, int endy) {
		this.size = size;
		start = new Place(startx, starty, size);
		end = new Place(endx, endy, size);
		maze = new Place[size][size];
		maze[starty][startx] = start;
		maze[endy][endx] = end;
		if (maze == null) {
			System.out.println("Allocation failed.");
			throw new NullPointerException();
		}
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				if (!(i == starty && j == startx || i == endy && j == endx)) {
					maze[i][j] = new Place(i, j, size);
					maze[i][j].wall = false;
				}
			}
	}

	////////////////////////////////////////////

	public boolean addWall(int x, int y) {

		// here are all the checks.
		if (x > end.getX() || x < start.getX())
			throw new IllegalArgumentException();

		if (y > end.getY() || y < start.getY())
			throw new IllegalArgumentException();

		if (maze[x][y].wall == true)
			throw new IllegalArgumentException();

		// if we reached this place, then no Exception thrown and everything is good.
		else {
			maze[x][y].wall = true;
			return true;
		}
	}

	////////////////////////////////////////////
	
	// NOTE: using StringBuilder is better than regular String in terms of Space-Complexity.
	
	public String toString() {

		String s = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == start.getX() && j == start.getY()) {
					s += ' ';
					s += "S";
				} else {
					if (i == end.getX() && j == end.getY()) {
						s += ' ';
						s += "E";
					} else {
						if (maze[i][j].wall == true) {
							s += ' ';
							s += "@";
						} else {
							s += ' ';
							s += ".";
						}
					}
				}
			}
			s += "\n";
		}
		return s;
	}

	public boolean isSolvable() {
		Graph<Place> g = new Graph<>();

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (maze[i][j].wall == false) {
					try {
						g.addVertex(maze[i][j]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		// added all the vertices (that should be added) to the graph.
		// now we have to add the edges between all the adjacency vertices.
		// check the columns.
		int i = 0, j = 0;
		while (j < size) {
			while (i +1 < size) {
				if (maze[i][j].wall == false && maze[i+1][j].wall == false) {
					try {
						g.addEdge(maze[i][j], maze[i+1][j]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				i++;
			}
			j++;
			i=0;
		}

		// check the rows.
		i = 0;
		j = 0;
		while (i < size) {
			while (j+1 < size) {
				if (maze[i][j].wall == false && maze[i][j+1].wall == false) {
					try {
						g.addEdge(maze[i][j], maze[i][j+1]);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				j++;
			}
			i++;
			j=0;
		}

		try {
			if (g.connected(start, end) == true)
				return true;
		} catch (GraphException e) {
			e.printStackTrace();
		}
		return false;
	}

	////////////////////////////////////////////

	public static void main(String[] args) throws GraphException {
		Maze m = new Maze(4, 0, 0, 3, 3);
		m.addWall(1, 1);
		m.addWall(3, 1);
		m.addWall(0, 1);
		m.addWall(2, 3);
		// m.addWall(2, 3); ***************** IllegalArgumentException
		// *********************
		m.addWall(1, 3);
		System.out.println(m);
		System.out.println(m.isSolvable());

	}

}

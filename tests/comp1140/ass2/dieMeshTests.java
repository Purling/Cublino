package comp1140.ass2;

import comp1140.ass2.gui.Viewer;
import javafx.beans.Observable;
import javafx.collections.ObservableFloatArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 1000, unit = MILLISECONDS)
class dieMeshTests {

    @Test
    public void meshVertexValidityTest() {
        Viewer.createDieMesh();
        float[] vertices = new float[Viewer.dieMesh.getPoints().size()];
        Viewer.dieMesh.getPoints().toArray(vertices);
        assertEquals(8*3, vertices.length,
                "The vertex array must contain 3 points per vertex for 8 vertices");
        boolean[] hasSeen = new boolean[8];
        for (int point = 0; point < 24; point += 3) {
            int binary = (int) (4*vertices[point] + 2*vertices[point+1] + vertices[point+2]+7)/2;
            assertTrue(0 <= binary && binary < 8,
                    "Each vertex must be at a corner of the unit cube");
            hasSeen[binary] = true;
        }
        for (int binary = 0; binary < 8; binary++) {
            assertTrue(hasSeen[binary],
                    "Vertex is missing a corner of the unit cube");
        }
    }

    @Test
    public void meshUvsValidityTest() {
        float[] uvs = new float[Viewer.dieMesh.getTexCoords().size()];
        Viewer.dieMesh.getTexCoords().toArray(uvs);

        assertTrue(uvs.length % 2 == 0,
                "UV array should have an even length");
        float prevX = 0;
        float prevY = 0;

        for (int coordinate = 0; coordinate < uvs.length; coordinate += 2) {
            float x = uvs[coordinate];
            float y = uvs[coordinate+1];

            assertTrue(0 <= x & y <= 1,
                    "UV x-coordinate out of bounds");
            assertTrue(Math.round(x*4) == x*4,
                    "UV x-coordinate doesn't misaligned edge of die face");

            assertTrue(0 <= y & y <= 0.75,
                    "UV y-coordinate out of bounds for given net");
            assertTrue(Math.round(y*4) == y*4,
                    "UV y-coordinate doesn't misaligned edge of die face");

            assertTrue(!(x == 0 || x == 0.75 || x == 1) || !(y == 0 || y == 0.75),
                    "UV coordinate outside the faces of the net");

            assertTrue((x > prevX && y >= prevY) || y > prevY,
                    "UV coordinates not in left-to-right, top-to-bottom order");
            prevX = x;
            prevY = y;
        }
    }

    @Test
    public void meshFacesValidityTest() {
        int[] faces = new int[Viewer.dieMesh.getFaces().size()];
        Viewer.dieMesh.getFaces().toArray(faces);
        assertEquals((3+3)*2*6, faces.length,
                "The face array must contain (3 vertices + 3 UVs) per vertex for 2 triangles for 6 faces");
        for (int point = 0; point < 72; point += 6) {
            for (int vertex = 0; vertex < 6; vertex += 2) {
                assertTrue(faces[point + vertex] <= 8,
                        "Vertex in mesh array does not refer to a cube corner");
            }
            for (int uv = 1; uv < 6; uv += 2) {
                assertTrue(faces[point + uv] <= 14,
                        "UV in mesh array is non-existent");
            }
            int a = faces[point];
            int b = faces[point+2];
            int c = faces[point+4];
            assertTrue((a/4) == (b/4) && (b/4) == (c/4)
                            || a%2 == b%2 && b%2 == c%2
                            || (a%4)/2 == (b%4)/2 && (b%4)/2 == (c%4)/2,
                    "Each triangular face must lie in one of the axial planes");
        }
    }
}
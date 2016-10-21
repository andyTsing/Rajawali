package c.org.rajawali3d.object.renderers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import android.opengl.GLES20;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import c.org.rajawali3d.GlTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rajawali3d.materials.Material;

/**
 * @author Jared Woolston (jwoolston@keywcorp.com)
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ObjectRendererImplTest extends GlTestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void ensureState() throws Exception {
        final Material material = mock(Material.class);
        final ObjectRendererImpl renderer = new ObjectRendererImpl(material, true, false, true, true,
                                                                   GLES20.GL_ONE, GLES20.GL_ONE_MINUS_DST_ALPHA,
                                                                   GLES20.GL_LEQUAL);
        runOnGlThreadAndWait(new Runnable() {
            @Override public void run() {
                renderer.ensureState(null);
            }
        });

        final boolean[] boolOutput = new boolean[1];
        final int[] intOutput = new int[2];

        // Check double sided is enabled - no face culling
        runOnGlThreadAndWait(new Runnable() {
            @Override public void run() {
                GLES20.glGetBooleanv(GLES20.GL_CULL_FACE, boolOutput, 0);
                assertFalse(boolOutput[0]);
            }
        });

        // Skip backside check

        // Check blending is enabled
        runOnGlThreadAndWait(new Runnable() {
            @Override public void run() {
                GLES20.glGetBooleanv(GLES20.GL_BLEND, boolOutput, 0);
            }
        });
        assertTrue("Unexpected blending state: " + boolOutput[0], boolOutput[0]);

        // Check blend source factor
        runOnGlThreadAndWait(new Runnable() {
            @Override public void run() {
                GLES20.glGetIntegerv(GLES20.GL_BLEND_SRC_RGB, intOutput, 0);
                GLES20.glGetIntegerv(GLES20.GL_BLEND_SRC_ALPHA, intOutput, 1);
            }
        });
        assertEquals("Unexpected blend source RGB function: " + intOutput[0], GLES20.GL_ONE, intOutput[0]);
        assertEquals("Unexpected blend source ALPHA function: " + intOutput[1], GLES20.GL_ONE, intOutput[1]);

        // Check blend destination factor
        runOnGlThreadAndWait(new Runnable() {
            @Override public void run() {
                GLES20.glGetIntegerv(GLES20.GL_BLEND_DST_RGB, intOutput, 0);
                GLES20.glGetIntegerv(GLES20.GL_BLEND_DST_ALPHA, intOutput, 1);
            }
        });
        assertEquals("Unexpected blend destination RGB function: " + intOutput[0], GLES20.GL_ONE_MINUS_DST_ALPHA,
                     intOutput[0]);
        assertEquals("Unexpected blend destination ALPHA function: " + intOutput[1], GLES20.GL_ONE_MINUS_DST_ALPHA,
                     intOutput[1]);

        // Check depth testing is enabled
        runOnGlThreadAndWait(new Runnable() {
            @Override public void run() {
                GLES20.glGetBooleanv(GLES20.GL_DEPTH_TEST, boolOutput, 0);
            }
        });
        assertTrue("Unexpected depth test state: " + boolOutput[0], boolOutput[0]);

        // Check depth test equation
        runOnGlThreadAndWait(new Runnable() {
            @Override public void run() {
                GLES20.glGetIntegerv(GLES20.GL_DEPTH_FUNC, intOutput, 0);
            }
        });
        assertEquals("Unexpected depth function: " + intOutput[0], GLES20.GL_LEQUAL, intOutput[0]);
    }

    @Test
    public void setCameraMatrices() throws Exception {

    }

    @Test
    public void prepareForObject() throws Exception {

    }

    @Test
    public void issueDrawCalls() throws Exception {

    }

    @Test
    public void getMaterial() throws Exception {

    }

    @Test
    public void isDoubleSided() throws Exception {

    }

    @Test
    public void isBackSided() throws Exception {

    }

    @Test
    public void isBlended() throws Exception {

    }

    @Test
    public void isDepthTestEnabled() throws Exception {

    }

    @Test
    public void getBlendSourceFactor() throws Exception {

    }

    @Test
    public void getBlendDestinationFactor() throws Exception {

    }

    @Test
    public void getDepthFunction() throws Exception {

    }
}
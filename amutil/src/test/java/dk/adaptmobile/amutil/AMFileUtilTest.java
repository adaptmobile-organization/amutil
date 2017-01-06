package dk.adaptmobile.amutil;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.test.mock.MockContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import dk.adaptmobile.amutil.io.AMFileUtil;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Created by bjarkeseverinsen on 06/01/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class AMFileUtilTest {

    @Mock Context mockContext;
    @Mock File file;

    @Mock AssetManager assetManager;
    @Mock InputStream inputStream;

    @Mock Environment environment;

    @Before
    public void setup() {
//        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBasePathStringTest() {
        String basepath = "/basepath";

        //retarded test, was messing around with mockito ^
        when(mockContext.getFilesDir()).thenReturn(file);
        when(file.getAbsolutePath()).thenReturn(basepath);

        assertEquals(basepath + "/", AMFileUtil.getBasePath(mockContext, true));
        assertEquals(basepath, AMFileUtil.getBasePath(mockContext, false));
    }


    @Test
    public void loadJSONFromAssetTest() throws IOException {

        String jsonToTest = "{ \"name\":\"John\", \"age\":31, \"city\":\"New York\" }";
        String mockJsonFileName = "testJson.json";

        FileInputStream inputStream = new FileInputStream(getFileFromPath(mockJsonFileName));

        doReturn(assetManager).when(mockContext).getAssets();
        doReturn(inputStream).when(assetManager).open(mockJsonFileName);

        assertEquals("", AMFileUtil.loadJSONFromAsset(null, null));
        assertEquals("", AMFileUtil.loadJSONFromAsset(null, ""));
        assertEquals("", AMFileUtil.loadJSONFromAsset(mockContext, null));

        assertEquals(jsonToTest, AMFileUtil.loadJSONFromAsset(mockContext, mockJsonFileName));

    }

    @Test
    public void getInternalFileTest() {
        assertNull(AMFileUtil.getInternalFile(mockContext, null));
        //Doesn't make sense testing if the file can be gotten unless it's a real android environment
    }

    @Test
    public void getExternalFileTest() {
        assertNull(AMFileUtil.getExternalFile(null));
    }

    @Test
    public void copyInternalFileTest() throws IOException {
        File input = getFileFromPath("testJson.json");
        File output = new File("src/test/resources/testJsonCopy.json");

        assertFalse(AMFileUtil.copyInternalFile(null, null));
        assertFalse(AMFileUtil.copyInternalFile(input, null));
        assertFalse(AMFileUtil.copyInternalFile(null, output));

        assertTrue(AMFileUtil.copyInternalFile(input, output));
    }

    @Test
    public void removeFileTest() {
        //test with a nonexisting file
        assertFalse(AMFileUtil.removeFile(new File("src/test/resources/nonexistingfile.json")));

        //test with an existing file
        assertTrue(AMFileUtil.removeFile(new File("src/test/resources/testJsonCopy.json")));

        //test the same file again (now nonexisting)
        assertFalse(AMFileUtil.removeFile(new File("src/test/resources/testJsonCopy.json")));
    }

    private File getFileFromPath(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }


}

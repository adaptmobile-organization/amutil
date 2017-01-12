package dk.adaptmobile.amutil;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import dk.adaptmobile.amutil.io.AMFileUtil;
import dk.adaptmobile.amutil.misc.AMImageUtil;
import dk.adaptmobile.amutil.network.AMNetworkUtil;
import dk.adaptmobile.amutil.system.L;

/**
 * Created by Alex on 30/12/16.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AMDateUtilTest.class,
        AMFileUtilTest.class,
        AMImageUtilTest.class,
        AMImageUtilMockTest.class,
        AMUtilTest.class,
        AMNetworkUtilTest.class,
        AMLTest.class
})

public class TestSuite {
}

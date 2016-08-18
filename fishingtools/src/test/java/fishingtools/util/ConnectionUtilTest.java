package fishingtools.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;


public class ConnectionUtilTest {
	
	@Test
	public void testGetConnection() throws SQLException {
		
		Connection connection = ConnectionUtil.getConnection();
		Assert.assertNotNull(connection);
	}

}

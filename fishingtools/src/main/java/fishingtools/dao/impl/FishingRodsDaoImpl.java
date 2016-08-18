package fishingtools.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import fishingtools.dao.FishingRodsDao;
import fishingtools.domain.FishingRods;
import fishingtools.util.ConnectionUtil;

public class FishingRodsDaoImpl implements FishingRodsDao {

	private static final Logger log = Logger.getLogger(FishingRodsDao.class.getName());

	private Connection conn;
	private PreparedStatement ps;

	@Override
	public boolean save(FishingRods rod) {

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO `fishingrods` (`type`, `length`, `power`, `material`, `number of pieces`, `date of manufacture`, `price`, `availble in stock`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			ps = conn.prepareStatement(sql);
			ps.setString(1,  rod.getType());
			ps.setDouble(2, rod.getLenght());
			ps.setString(3, rod.getPower().toString());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<FishingRods> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

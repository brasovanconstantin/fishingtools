package fishingtools.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import fishingtools.dao.FishingRodsDao;
import fishingtools.domain.FishingRods;
import fishingtools.domain.Power;
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
			ps.setString(1, rod.getType());
			ps.setDouble(2, rod.getLenght());
			ps.setString(3, rod.getPower().toString());
			ps.setString(4, rod.getMaterial());
			ps.setInt(5, rod.getNumberOfPieces());
			ps.setDate(6, new Date(rod.getDateOfManufacture().getTime()));
			ps.setDouble(7, rod.getPrice());
			ps.setInt(8, rod.getAvailableInStock());

			int affectedRows = ps.executeUpdate();
			log.info(String.format("Saved object, total affected rows: %d", affectedRows));
			return true;

		} catch (SQLException e) {

			// e.printStackTrace();
			log.severe(String.format("Exception: %s", e.getMessage()));
		}

		return false;
	}

	@Override
	public List<FishingRods> findAll() {

		List<FishingRods> rodList = new ArrayList<>();

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM fishingrods";
			ps = conn.prepareStatement(sql);
			ResultSet set = ps.executeQuery();

			while (set.next()) {
				long id = set.getInt("id");
				String type = set.getString("type");
				double length = set.getDouble("length");
				String powerValue = set.getString("power");
				Power power = Power.getPower(powerValue);
				String material = set.getString("material");
				int numberOfPieces = set.getInt("number of pieces");
				Date dateOfManufacture = set.getDate("date of manufacture");
				double price = set.getDouble("price");
				int availableInStock = set.getInt("availble in stock");

				FishingRods rod = new FishingRods();
				rod.setId(id);
				rod.setType(type);
				rod.setLenght(length);
				rod.setPower(power);
				rod.setMaterial(material);
				rod.setNumberOfPieces(numberOfPieces);
				rod.setDateOfManufacture(dateOfManufacture);
				rod.setPrice(price);
				rod.setAvailableInStock(availableInStock);
				rodList.add(rod);
				log.info(String.format("Added new user to list: %s", rod.toString()));
			}

		} catch (SQLException e) {

			// e.printStackTrace();
			log.severe(String.format("Fatal error: %s", e.getMessage()));
		}
		log.info(String.format("Retrieved from database %d users", rodList.size()));
		System.out.println(rodList);
		return rodList;
	}

	public void delete(long row) {
		// TODO Auto-generated method stub
		
	}

}

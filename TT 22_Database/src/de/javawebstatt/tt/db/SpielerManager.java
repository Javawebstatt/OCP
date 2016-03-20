package de.javawebstatt.tt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.javawebstatt.tt.spieler.Geschlecht;
import de.javawebstatt.tt.spieler.SpielerBuilder;
import de.javawebstatt.tt.spieler.SpielerI;

public class SpielerManager {

	private static final int COL_PUNKTE = 5;
	private static final int COL_GESCHLECHT = 4;
	private static final int COL_NACHNAME = 3;
	private static final int COL_VORNAME = 2;
	private static final int COL_KUERZEL = 1;
	private static SpielerManager instance;
	private static String conUrl = "jdbc:mysql://127.0.0.1:3306/ocp";
	private static String conUsr = "ocp";
	private static String conPwd = "ocp";

	private SpielerManager() throws Exception {

	}

	public static SpielerManager getInstance() throws Exception {
		if (instance == null)
			instance = new SpielerManager();
		return instance;
	}

	private String getPrefix(String vorname, String nachname) {
		return vorname.substring(0, 1).toUpperCase() + nachname.substring(0, 1).toUpperCase();
	}

	private void printSQLException(SQLException ex) {
		System.err.println(ex.getErrorCode());
		System.err.println(ex.getSQLState());
		System.err.println(ex.getMessage());
		ex.printStackTrace();
	}

	public String createNeuesKürzel(String vorname, String nachname) {
		Object currentNum = ermittleAnzahlKürzel(vorname, nachname);
		String neuesKürzel = String.format("%1$s%2$02d", getPrefix(vorname, nachname), currentNum);

		return neuesKürzel;
	}

	public int deleteAlleKürzel(String vorname, String nachname) {
		int count = -1;

		try (Connection con = DriverManager.getConnection(conUrl, conUsr, conPwd)) {
			PreparedStatement stmt = con.prepareStatement("delete from spieler where kuerzel like ?");
			String prefix = getPrefix(vorname, nachname);
			stmt.setString(1, prefix + '%');
			System.out.println(stmt.toString());
			count = stmt.executeUpdate();
		} catch (SQLException ex) {
			printSQLException(ex);
		}

		return count;
	}

	public int ermittleAnzahlKürzel(String vorname, String nachname) {
		int count = -1;

		try (Connection con = DriverManager.getConnection(conUrl, conUsr, conPwd)) {
			Statement statement = con.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String prefix = getPrefix(vorname, nachname);
			ResultSet resultSet = statement
					.executeQuery("select count(*) from spieler where kuerzel like '" + prefix + "%'");
			if (resultSet.first())
				count = resultSet.getInt(1);
		} catch (SQLException ex) {
			printSQLException(ex);
		}

		return count;
	}

	public int erzeugeNeuenSpieler(SpielerI spieler) {
		int count = -1;
		try (Connection con = DriverManager.getConnection(conUrl, conUsr, conPwd)) {
			PreparedStatement prepStmt = con.prepareStatement("insert into spieler values(?,?,?,?,?)");
			prepStmt.setNString(COL_KUERZEL, spieler.getKürzel());
			prepStmt.setString(COL_VORNAME, spieler.getVorname());
			prepStmt.setString(COL_NACHNAME, spieler.getNachname());
			prepStmt.setString(COL_GESCHLECHT, spieler.getGeschlecht().toString());
			prepStmt.setInt(COL_PUNKTE, spieler.getPunktezahl());
			count = prepStmt.executeUpdate();
		} catch (SQLException ex) {
			printSQLException(ex);
		}

		return count;
	}

	private SpielerI erzeugeSpielerFromRS(ResultSet resultSet) throws SQLException {
		Geschlecht geschlecht = Geschlecht.valueOf(resultSet.getString(4));
		SpielerI spieler = new SpielerBuilder().setzeKürzel(resultSet.getString(1)).setzeGeschlecht(geschlecht)
				.setzeVorname(resultSet.getString(2)).setzeNachname(resultSet.getString(3))
				.setzePunktezahl(resultSet.getInt(5)).build();
		return spieler;
	}

	public SpielerI ladeSpieler(String kürzel) {
		SpielerI spielerFromDB = null;

		try (Connection con = DriverManager.getConnection(conUrl, conUsr, conPwd)) {
			PreparedStatement prepStmt = con.prepareStatement("select * from spieler where kuerzel = ?",
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			prepStmt.setString(COL_KUERZEL, kürzel);
			ResultSet resultSet = prepStmt.executeQuery();
			if (resultSet.first()) {
				spielerFromDB = erzeugeSpielerFromRS(resultSet);
			}
		} catch (SQLException ex) {
			printSQLException(ex);
		}

		return spielerFromDB;
	}

	public SpielerI sucheSpielerEinfach(String vorname3, String nachname3, int suchPunkte) {
		SpielerI spielerFromDB = null;
		try (Connection con = DriverManager.getConnection(conUrl, conUsr, conPwd)) {
			PreparedStatement stmt = con.prepareStatement("select * from spieler where kuerzel like ?",
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String prefix = getPrefix(vorname3, nachname3);
			stmt.setString(1, prefix + '%');
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt(COL_PUNKTE) == suchPunkte)
					return erzeugeSpielerFromRS(resultSet);
			}
		} catch (SQLException ex) {
			printSQLException(ex);
		}

		return spielerFromDB;
	}

	public SpielerI sucheSpielerSchneller(String vorname3, String nachname3, int suchPunkte) {
		SpielerI spielerFromDB = null;
		try (Connection con = DriverManager.getConnection(conUrl, conUsr, conPwd)) {
			PreparedStatement stmt = con.prepareStatement("select * from spieler where kuerzel like ? order by punkte",
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String prefix = getPrefix(vorname3, nachname3);
			stmt.setString(1, prefix + '%');
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.last()) {
				int maxNum = resultSet.getRow();
				int position = maxNum / 2;
				int offset = maxNum / 2;
				for (int attempts = 0; attempts <= 2 * maxNum; attempts++) {
					offset /= 2;
					resultSet.absolute(position);
					System.out
							.println("position=" + position + ",offset=" + offset + ",attempts=" + attempts + ",kürzel="
									+ resultSet.getString(COL_KUERZEL) + ",punkte=" + resultSet.getInt(COL_PUNKTE));
					if (resultSet.getInt(COL_PUNKTE) == suchPunkte) {
						System.out.println("JUHU GEFUNDEN");
						return erzeugeSpielerFromRS(resultSet);
					} else if (resultSet.getInt(COL_PUNKTE) > suchPunkte) {
						position -= (offset > 0) ? offset : 1;
						System.out.println("position -= offset -> position=" + position);
					} else {
						position += (offset > 0) ? offset : 1;
						System.out.println("position += offset -> position=" + position);
					}
				}
			}
		} catch (SQLException ex) {
			printSQLException(ex);
		}

		return spielerFromDB;
	}

	public SpielerI sucheSpielerSchnellerUndEleganter(String vorname3, String nachname3, int suchPunkte) {
		SpielerI spielerFromDB = null;
		try (Connection con = DriverManager.getConnection(conUrl, conUsr, conPwd)) {
			PreparedStatement stmt = con.prepareStatement("select * from spieler where kuerzel like ? order by punkte",
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String prefix = getPrefix(vorname3, nachname3);
			stmt.setString(1, prefix + '%');
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.last()) {
				int maxNum = resultSet.getRow();
				resultSet.absolute(maxNum / 2);
				int offset = maxNum / 2;
				for (int attempts = 0; attempts <= 2 * maxNum; attempts++) {
					offset = offset >= 2 ? offset / 2 : 1;
					System.out.println("resultSet.getRow()=" + resultSet.getRow() + ",offset=" + offset + ",attempts="
							+ attempts + ",kürzel=" + resultSet.getString(COL_KUERZEL) + ",punkte="
							+ resultSet.getInt(COL_PUNKTE));
					if (resultSet.getInt(COL_PUNKTE) == suchPunkte) {
						System.out.println("JUHU GEFUNDEN");
						return erzeugeSpielerFromRS(resultSet);
					} else if (resultSet.getInt(COL_PUNKTE) > suchPunkte) {
						resultSet.relative(-offset);
					} else {
						resultSet.relative(offset);
					}
				}
			}
		} catch (SQLException ex) {
			printSQLException(ex);
		}

		return spielerFromDB;
	}

}

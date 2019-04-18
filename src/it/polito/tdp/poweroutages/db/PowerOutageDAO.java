package it.polito.tdp.poweroutages.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.model.Evento;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<Evento> getEventList(Nerc n) {

		String sql = "SELECT nerc.VALUE, nerc.id, p.customers_affected, p.date_event_began,p.date_event_finished, p.id " + 
				"FROM poweroutages AS p, nerc " + 
				"WHERE nerc.id=p.nerc_id AND nerc.id=? ORDER BY p.date_event_began";
		List<Evento> listaEventi = new LinkedList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,n.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Evento e = new Evento(res.getInt("p.id"),n,res.getLong("p.customers_affected"),res.getTimestamp("p.date_event_began").toLocalDateTime(),res.getTimestamp("p.date_event_finished").toLocalDateTime());
				listaEventi.add(e);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return listaEventi;
	}
	
	
	
}

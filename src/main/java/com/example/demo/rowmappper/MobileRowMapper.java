package com.example.demo.rowmappper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.example.demo.entity.Mobiles;


public class MobileRowMapper  implements RowMapper<Mobiles>{

@Override
public Mobiles mapRow(ResultSet rs, int rowNum) throws SQLException {
	Mobiles mobiles =new Mobiles();
	
	mobiles.setId(rs.getInt("id"));
	mobiles.setMobile(rs.getString("mobile"));
	mobiles.setOs(rs.getString("os"));
	
	return mobiles;
}

}

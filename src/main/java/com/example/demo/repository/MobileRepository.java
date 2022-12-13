package com.example.demo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Mobiles;
import com.example.demo.rowmappper.MobileRowMapper;

@Repository
public class MobileRepository {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert simplejdbcinsert;
	@Autowired
	private NamedParameterJdbcTemplate namedjdbcparameterjdbcTemplate;
	
	
	@PostConstruct
	private void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		simplejdbcinsert = new SimpleJdbcInsert(dataSource).withTableName("mobiles").withSchemaName("public")
				.usingGeneratedKeyColumns("id");
	}

	public List<Mobiles> getAllMobiles() {
		return namedjdbcparameterjdbcTemplate.query("select * from mobiles", new MobileRowMapper());
	}

	public List<Mobiles> getMobileByid(int id) {
		Map<String,Object> map =new HashMap<>();
		map.put("id", id);
		return  namedjdbcparameterjdbcTemplate.query("select * from mobiles where id=:id",map, new MobileRowMapper());
	}

	public Mobiles saveMobile(Mobiles mobiles) {
		SqlParameterSource save =new BeanPropertySqlParameterSource(mobiles);
		mobiles.setId((int) simplejdbcinsert.executeAndReturnKey(save));;
		return mobiles;
	}

	public Mobiles update(Mobiles mobiles) {
		SqlParameterSource update = new BeanPropertySqlParameterSource(mobiles);
		 namedjdbcparameterjdbcTemplate.update("update mobiles SET mobile=:mobile,os=:os where id=:id",update);
		return mobiles;
	}

	

}

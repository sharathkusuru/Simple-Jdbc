package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Mobiles;
import com.example.demo.repository.MobileRepository;

@RestController
public class MobileController {
	@Autowired
	MobileRepository mobileRepository;

	@GetMapping("/mobiles")
	public List<Mobiles> getAllMobiles() {
		List<Mobiles> mobiles = mobileRepository.getAllMobiles();
		return mobiles;
	}
	@GetMapping("/mobile/{id}")
	public List<Mobiles> getMobileByid(@PathVariable("id") int id){
		return  mobileRepository.getMobileByid(id);
		
	}
	@PostMapping("/new")
	public Mobiles saveMobile (@RequestBody Mobiles mobiles) {
		return  mobileRepository.saveMobile(mobiles);
	}
    @PutMapping("/update/{id}")
    public Mobiles update(@RequestBody Mobiles mobiles,@PathVariable int id) {
    	return mobileRepository.update(mobiles);
    }

}

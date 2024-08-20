package com.property;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class PropertyController {

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private Validator validator;

	@PostMapping("/upload-csv")
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
		}

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
			String line;
			List<Property> properties = new ArrayList<>();
			boolean isFirstLine = true;
			while ((line = reader.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue; // Skip the header line

				}
				String[] data = line.split(",");

				if (data.length != 11) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Invalid CSV format. Each line must have 11 columns. Line number= "+line);
				}

				Property property = new Property();
				property.setPrefecture(data[0]);
				property.setCity(data[1]);
				property.setTown(data[2]);
				property.setChome(data[3]);
				property.setDanchi(data[4]);
				property.setGo(data[5]);
				property.setBuilding(data[6]);
				property.setPrice(data[7]);
				property.setNearestStation(data[8]);
				property.setPropertyType(data[9]);
				property.setLandArea(data[10]);

				// Concatenate full address
				String fullAddress = String.join(", ", data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
				property.setFullAddress(fullAddress);

				properties.add(property);
			}

			// Validate properties
			for (Property property : properties) {
				BindingResult result = new BeanPropertyBindingResult(property, "property");
				validator.validate(property, result);

				if (result.hasErrors()) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Validation error: " + result.getAllErrors().toString());
				}

			}
			propertyRepository.deleteAll(); // Delete previous data

			propertyRepository.saveAll(properties); // Save new data
			
			propertyRepository.flush(); // Ensure data is immediately written to the database

			return ResponseEntity.status(HttpStatus.OK).body("File uploaded and data saved successfully");
		} catch (ArrayIndexOutOfBoundsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid CSV format. Each line must have 11 columns.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
		}
	}

	@GetMapping("/properties")
	public ResponseEntity<List<Property>> getAllProperties() {
		List<Property> properties = propertyRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(properties);
	}
}
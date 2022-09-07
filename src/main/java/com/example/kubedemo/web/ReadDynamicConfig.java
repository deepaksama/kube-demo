package com.example.kubedemo.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadDynamicConfig {

	@Value("${logging.configfile.name}")
	private String loggingConfigFilename;

	@GetMapping(path = { "/getconfig" })
	public String getConfig() {
		String fileContent = "";
		Map<String, String> config = new HashMap<>();
		try {
			fileContent = Files.readString(Path.of(loggingConfigFilename));
			String[] valuePairs = fileContent.split(System.lineSeparator());

			Arrays.asList(valuePairs).stream().forEach(pair -> {
				String key = pair.split("=")[0];
				String value = pair.split("=")[1];
				config.put(key, value);
			});

			List<String> configList = config.entrySet().stream()
					.map(entry -> String.format("(%s,%s)", entry.getKey(), entry.getValue()))
					.collect(Collectors.toList());
			return configList.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String alternativeReadding(String fileName) {
		Map<String, String> config = new HashMap<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line = "";
			while (null != line) {
				line = reader.readLine();
				String key = line.split("=")[0];
				String value = line.split("=")[1];
				config.put(key, value);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
		List<String> configList = config.entrySet().stream()
				.map(entry -> String.format("(%s,%s)", entry.getKey(), entry.getValue())).collect(Collectors.toList());
		return configList.toString();
	}
}

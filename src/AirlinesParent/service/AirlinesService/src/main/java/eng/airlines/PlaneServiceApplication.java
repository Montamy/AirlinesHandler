package eng.airlines;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.module.SimpleModule;

import eng.airlines.model.interfaces.AirlineModelInterface;
import eng.airlines.model.interfaces.CityModelInterface;
import eng.airlines.server.deserializer.AirlinesEntityDeserializer;
import eng.airlines.server.deserializer.CitiesEntityDeserializer;


@SpringBootApplication
public class PlaneServiceApplication {

	private static Logger logger = LogManager.getLogger(PlaneServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PlaneServiceApplication.class, args);
		logger.info("Start Plane Service Application.");
	}

	@Bean
	public SimpleModule airlinesEntityDeserializer() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(AirlineModelInterface.class, new AirlinesEntityDeserializer());
		return module;
	}

	@Bean
	public SimpleModule cityEntityDeserializer() {
		SimpleModule module = new SimpleModule();
		module.addDeserializer(CityModelInterface.class, new CitiesEntityDeserializer());
		return module;
	}


}

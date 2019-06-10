package com.org.test.elk;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ELKController {
	private static final Logger LOG = Logger.getLogger(ELKController.class.getName());

	@RequestMapping(value = "/elk")
	public String helloWorld() {
		String response = "Hello there. Today date is: " + new Date();
//		LOG.log(Level.INFO, response);
		LOG.info(response);
		return response;
	}

	@RequestMapping(value = "/exception")
	public String exception() {
		String response = "";
		try {
			throw new Exception("Opps Exception raised....");
		} catch (Exception e) {
			e.printStackTrace();
//			LOG.error(e);
			LOG.log(Level.SEVERE, "", e);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stackTrace = sw.toString();
//			LOG.error("Exception - " + stackTrace);
			LOG.log(Level.SEVERE, "", "Exception - " + stackTrace);
			response = stackTrace;
		}

		return response;
	}
	
}

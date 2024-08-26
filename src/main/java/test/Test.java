package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {

	private static final Logger logger = LogManager.getLogger(Test.class);

	public static void main(String[] args) {
		logger.debug("---DEBUG---");
		logger.error("---ERROR---");
		logger.fatal("---FATAL---");
		logger.info("---INFO---");
		logger.trace("---TRACE---");
		logger.warn("---WARN---");

	}

}

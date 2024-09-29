package com.techcourse;

import com.interface21.HandlerManagementManager;
import java.io.IOException;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private static final int DEFAULT_PORT = 8080;

    public static void main(final String[] args) throws Exception {
//        System.out.println(Thread.currentThread().threadId() + "----Application. Thread Id");
//        System.out.println(Thread.currentThread().getContextClassLoader().getName() + " ---Application. Thread ClassLoader");
//        System.out.println(Reflections.class.getClassLoader().getName() + " ---Application. Reflections.class.getClassLoader");
        HandlerManagementManager handlerManagementManager = HandlerManagementManager.getInstance();
        handlerManagementManager.initialize(Application.class);

        final int port = defaultPortIfNull(args);
        final var tomcat = new TomcatStarter(port);
        log.info("configuring app with basedir: {}", TomcatStarter.WEBAPP_DIR_LOCATION);

        tomcat.start();
        stop(tomcat);
    }

    private static int defaultPortIfNull(final String[] args) {
        return Stream.of(args)
                .findFirst()
                .map(Integer::parseInt)
                .orElse(DEFAULT_PORT);
    }

    private static void stop(final TomcatStarter tomcat) {
        try {
            // make the application wait until we press any key.
            System.in.read();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            log.info("web server stop.");
            tomcat.stop();
        }
    }
}

package com.issac.neo4j;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.*;
import org.neo4j.driver.net.ServerAddress;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: ywy
 * @date: 2019-10-22
 * @desc:
 */
public class ClusterTest {

    @Test
    void test() {
        addPerson("王五h");
    }

    private Driver createDriver(String virtualUri, String user, String password, ServerAddress... addresses) {
        Config config = Config.builder()
                .withResolver(address -> new HashSet<>(Arrays.asList(addresses)))
                .build();
        return GraphDatabase.driver(virtualUri, AuthTokens.basic(user, password), config);
    }

    private void addPerson(String name) {
        String username = "neo4j";
        String password = "123456";

        try (Driver driver = createDriver("bolt://eureka8100.com", username, password,
                ServerAddress.of("localhost", 8687),
                ServerAddress.of("localhost", 7687),
                ServerAddress.of("eureka8200.com", 9687))) {
            try (Session session = driver.session()) {
                session.run("CREATE (a:Person{name:$name})", parameters("name", name));
            }
        }
    }

    private Map<String, Object> parameters(String name, String value) {

        Map<String, Object> map = new HashMap<>();
        map.put(name, value);
        return map;
    }
}

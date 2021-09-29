package classes.sql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class MysqlConfigTest {
    MysqlConfig mysqlConfig;
    private String configString = "mysql.host=host\n" +
            "mysql.user=user\n" +
            "mysql.password=passphrase\n" +
            "mysql.table_name=table_name";


    @BeforeEach
    void setUp() {
        mysqlConfig = new MysqlConfig();
    }

    @Test
    @DisplayName("Should set given properties to mysqlConfig instance")
    void testSetLocalPropertiesToConfig() {
        Properties props = new Properties();
        try {
            props.load(new StringReader(this.configString));
            mysqlConfig.setLocalPropertiesToConfig(props);

            assertAll("mysqlConfig",
                    () -> assertEquals("host", mysqlConfig.getHost()),
                    () -> assertEquals("user", mysqlConfig.getUser()),
                    () -> assertEquals("passphrase", mysqlConfig.getPassword()),
                    () -> assertEquals("table_name", mysqlConfig.getDatabase())
            );
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
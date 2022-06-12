package TradingSystem.server.Service.ConfigurationTests;

import TradingSystem.server.Domain.Utils.Exception.ExitException;
import TradingSystem.server.Service.MarketSystem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ConfigurationTests {

    // services
        // good
    private static final String tests_external_services_path =  "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\external_services\\tests_external_services.txt";

    private static final String real_external_services_path =  "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\external_services\\real_external_services.txt";
        // bad
    private static final String bad_external_services_path =  "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\external_services\\bad_external_services.txt";

    // instructions
        // good
    private static final String empty_test_path =  "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\good_instructions\\empty_test.txt";
    private static final String goodIns1_path = "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\good_instructions\\good_inst1.txt";
    private static final String goodIns2_path = "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\good_instructions\\good_inst2.txt";
    private static final String goodIns3_path = "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\good_instructions\\good_inst3.txt";
    private static final String goodIns4_path = "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\good_instructions\\good_inst4.txt";
    private static final String goodIns5_path = "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\good_instructions\\good_inst5.txt";
    private static final String goodIns6_path = "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\good_instructions\\good_inst6.txt";
        // bad
    private static final String wrong_format_test_path = "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\bad_instructions\\wrong_format_test.txt";
    private static final String wrong_instruction_test_path = "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\bad_instructions\\wrong_instruction_test.txt";
    private static final String wrong_order_test_path = "..\\server\\src\\test\\java\\TradingSystem\\server\\Service\\ConfigurationTests\\bad_instructions\\wrong_order_test_path.txt";


 // -- Start

    static Stream<Arguments> bad_instructions() {
        return Stream.of(
                arguments(wrong_format_test_path),
                arguments(wrong_instruction_test_path),
                arguments(wrong_order_test_path)
        );
    }
    @ParameterizedTest
    @MethodSource("bad_instructions")
    void system_init_bad_instructions(String instructions_config_path){
        boolean answer =  false;
        try{

            MarketSystem marketSystem = new MarketSystem(tests_external_services_path, instructions_config_path);
        }
        catch (ExitException e){
            answer = true;
        }
        assertFalse(answer);
    }


    /**
     * empty_test_path -> with 0 instructions
     * goodIns1 : register & logout
     * goodIns2 : with spaces, register & logout & login
     * goodIns3 : more then 1 user.
     */
    static Stream<Arguments> good_instructions() {
        return Stream.of(
                arguments(empty_test_path),
                arguments(goodIns1_path),
                arguments(goodIns2_path),
                arguments(goodIns3_path),
                arguments(goodIns4_path),
                arguments(goodIns5_path),
                arguments(goodIns6_path)
        );
    }
    @ParameterizedTest
    @MethodSource("good_instructions")
    void system_init_good_instructions(String instructions_config_path){
        boolean answer =  false;
        try{

            MarketSystem marketSystem = new MarketSystem(tests_external_services_path, instructions_config_path);
        }
        catch (ExitException e){
            answer = true;
        }
        assertFalse(answer);
    }




    static Stream<Arguments> good_services() {
        return Stream.of(
                arguments(tests_external_services_path),
                arguments(real_external_services_path)
        );
    }
    @ParameterizedTest
    @MethodSource("good_services")
    void system_init_good_services(String services_config_path){
        boolean answer =  false;
        try{

            MarketSystem marketSystem = new MarketSystem(services_config_path, empty_test_path);
        }
        catch (ExitException e){
            answer = true;
        }
        assertFalse(answer);
    }


    static Stream<Arguments> bad_services() {
        return Stream.of(
                arguments(bad_external_services_path)
        );
    }
    @ParameterizedTest
    @MethodSource("bad_services")
    void system_init_bad_services(String services_config_path){
        boolean answer =  false;
        try{

            MarketSystem marketSystem = new MarketSystem(services_config_path, empty_test_path);
        }
        catch (ExitException e){
            answer = true;
        }

        assertTrue(answer);
    }




    @Test
    void read_config_file(){
        String[] to_return = {"1", "1"};
        try{

            MarketSystem marketSystem = new MarketSystem(tests_external_services_path, empty_test_path);
            to_return = marketSystem.read_config_file(tests_external_services_path);
        }
        catch (ExitException e){
            fail();
        }
        assertEquals("external_services:demo", to_return[0]);
        assertEquals("database:demo", to_return[1]);
    }
}
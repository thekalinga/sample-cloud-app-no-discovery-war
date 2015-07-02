package com.acme.service.defaultcontext;

import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecBuilder;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.apache.http.HttpStatus;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static com.jayway.restassured.config.LogConfig.logConfig;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static com.jayway.restassured.module.mockmvc.config.RestAssuredMockMvcConfig.newConfig;
import static org.hamcrest.Matchers.is;

@RunWith(JUnitParamsRunner.class)
@SpringApplicationConfiguration(classes = DefaultContextApp.class)
@TransactionConfiguration
@WebIntegrationTest("server.port:0")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class })
public class DefaultContextResourceTests {

    private static MockMvcRequestSpecification jsonDefaultRequestSpec;
    private static ResponseSpecification jsonDefaultResponseSpec;

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Resource
    private WebApplicationContext webApplicationContext;

    @BeforeClass
    public static void setupClass() {
        jsonDefaultRequestSpec = new MockMvcRequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setConfig(newConfig().logConfig(logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
                .build();

        jsonDefaultResponseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }

    @Before
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @After
    public void teardown() {
        RestAssuredMockMvc.reset();
    }

    @Test
    @Parameters
//    @TestCaseName("getMovie_WithValidDetails_ShouldSucceed({0})")
    public void orderBeer_WithValidDetails_ShouldSucceed(DefaultContextResource.Person person) throws Exception {
        given().
                spec(jsonDefaultRequestSpec).
                body(person).
        when().
                post("/orderBeer").
        then().
                spec(jsonDefaultResponseSpec).
                statusCode(HttpStatus.SC_OK).
                body("name", is("HappyFlow"));
    }

    private DefaultContextResource.Person[] parametersForOrderBeer_WithValidDetails_ShouldSucceed() {
        return new DefaultContextResource.Person[] {
                DefaultContextResource.Person.builder().name("Mid Aged").age(30).build(),
                DefaultContextResource.Person.builder().name("Senior").age(90).build(),
                DefaultContextResource.Person.builder().name("Just Adult").age(18).build(),
        };
    }

    @Test
    @Parameters
//    @TestCaseName("getMovie_WithValidDetails_ShouldSucceed({0})")
    public void orderBeer_WithInvalidDetails_ShouldFail(DefaultContextResource.Person person) throws Exception {
        given().
                spec(jsonDefaultRequestSpec).
                body(person).
                when().
                post("/orderBeer").
                then().
                statusCode(HttpStatus.SC_OK).
                spec(jsonDefaultResponseSpec).
                body("name", is("NoSoEasy"));
    }

    private DefaultContextResource.Person[] parametersForOrderBeer_WithInvalidDetails_ShouldFail() {
        return new DefaultContextResource.Person[] {
                DefaultContextResource.Person.builder().name("Minor").age(17).build()
        };
    }

}


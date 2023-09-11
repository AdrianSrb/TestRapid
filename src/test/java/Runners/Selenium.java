package Runners;


import UI.WebPage.CalculatorPage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Selenium {


    CalculatorPage calculatorPage;


    public Selenium() {

        init();
    }
    public void init() {
        this.calculatorPage = new CalculatorPage();

    }

    @Test
    @DisplayName("Calculator Page Test")
    public void calculatorTest() {
        init();
        calculatorPage.goToCalculatorPage();
        calculatorPage.calculateSimpleTest();


    }
}

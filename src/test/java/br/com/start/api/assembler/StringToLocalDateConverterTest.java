package br.com.start.api.assembler;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class StringToLocalDateConverterTest {


    @InjectMocks
    private StringToLocalDateConverter stringToLocalDateConverter;

    @Test
    public void converterData(){
        String data = "20/02/2002";
        LocalDate convert = stringToLocalDateConverter.convert(data);

        Assertions.assertNotNull(convert);
        Assertions.assertEquals(LocalDate.of(2002, 2, 20), convert);

    }

}

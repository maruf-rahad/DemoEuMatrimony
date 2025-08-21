package com.eu.demomatrimony.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        // LocalDate -> String
        Converter<LocalDate, String> toStringDate = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().toString();

        // String -> LocalDate
        Converter<String, LocalDate> toLocalDate = ctx ->
                ctx.getSource() == null ? null : LocalDate.parse(ctx.getSource());

        modelMapper.addConverter(toLocalDate);
        modelMapper.addConverter(toStringDate);
        return new ModelMapper();
    }
}

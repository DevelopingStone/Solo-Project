package com.publicWifiSearch.domain.repostitory.publicWifi;

import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.PublicWifiDetail;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.address.Address;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.installation.Installation;
import com.publicWifiSearch.domain.model.publicWifi.publicWifiDetail.wifi.Wifi;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class JdbcLauncherTest {

    @Test
    public void Address_타입에_맞는_패키지_경로를_포함한_필드_이름_가져오기() throws ClassNotFoundException {

        // given
        String className = Address.class.getName();
        Field[] fields = Address.class.getDeclaredFields();

        // when
        Class<?> type = Class.forName(className);
        Field[] expectFields = type.getDeclaredFields();
        // then
        assertArrayEquals(expectFields, fields);
    }

    @Test
    public void Installation_타입에_맞는_패키지_경로를_포함한_필드_이름_가져오기() throws ClassNotFoundException {

        // given
        String className = Installation.class.getName();
        Field[] fields = Installation.class.getDeclaredFields();

        // when
        Class<?> type = Class.forName(className);
        Field[] expectFields = type.getDeclaredFields();
        // then
        assertArrayEquals(expectFields, fields);
    }

    @Test
    public void Wifi_타입에_맞는_패키지_경로를_포함한_필드_이름_가져오기() throws ClassNotFoundException {

        // given
        String className = Wifi.class.getName();
        Field[] fields = Wifi.class.getDeclaredFields();

        // when
        Class<?> type = Class.forName(className);
        Field[] expectFields = type.getDeclaredFields();
        // then
        assertArrayEquals(expectFields, fields);
    }

    @Test
    public void Address_타입에_맞는_필드_이름만_가져오기() throws ClassNotFoundException {

        // given
        String className = Address.class.getName();
        Field[] fields = Address.class.getDeclaredFields();
        List<String> onlyFieldNames = new ArrayList<>();
        for (Field field: fields){
            onlyFieldNames.add(field.getName());
        }

        // when
        Class<?> type = Class.forName(className);
        Field[] expectFields = type.getDeclaredFields();
        List<String> expectOnlyFieldNames = new ArrayList<>();
        for (Field field: expectFields){
            expectOnlyFieldNames.add(field.getName());
        }
        System.out.println(expectOnlyFieldNames);
        // then
        assertLinesMatch(expectOnlyFieldNames, onlyFieldNames);
    }

    @Test
    public void Address_타입이_갖는_메소드_가져오기() throws ClassNotFoundException {

        // given
        Class<?> publicWifiDetail = Class.forName(Address.class.getName());
        PublicWifiDetail address = new Address();
        Method[] actualMethods = address.getClass().getDeclaredMethods();
        System.out.println(Arrays.toString(actualMethods));
        // when
        Method[] methods = publicWifiDetail.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));

        assertArrayEquals(methods, actualMethods);

    }



}
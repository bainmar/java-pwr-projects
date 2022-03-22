package com.bartoszek.jtzweakreferences;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonalDataSpec {

    private Path folderOnePath= Paths.get("src/test/resources/personal-data/00000000001");

    @Test
    void shouldBePossibleCreateObjectPersonalDataIfPathContainsImageAndRecord() throws IOException {
        PersonalData entry = PersonalData.createEntry(folderOnePath);
        assertThat(entry).isNotNull();
    }


}
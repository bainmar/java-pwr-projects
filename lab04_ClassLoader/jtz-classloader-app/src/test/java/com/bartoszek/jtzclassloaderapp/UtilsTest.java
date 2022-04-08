package com.bartoszek.jtzclassloaderapp;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {
    private Path pathToModules = Paths.get("../module_to_load");
    final String moduleName = "jtz.classloader.library";
    @Test
    void pathWithOneModuleShouldContainsOneModule(){
        List<String> moduleNames = Utils.getModulesNameFromPath(pathToModules);
        assertThat(moduleNames.size()).isEqualTo(1);
    }

    @Test
    void pathWithModuleNameShouldReturnsClassNames() throws IOException {
        final List<String> listOfClasses = Utils.getListOfClasses(pathToModules, moduleName);
        assertThat(listOfClasses.size()).isEqualTo(3);
    }

    @Test
    void shouldCreateLayerForRootModulesInChosenDirectoryPathWithOneModule(){
        ModuleLayer moduleLayer = Utils.createModuleLayer(pathToModules, Set.of(moduleName));
        final int numberOfModulesInLayer = moduleLayer.modules().size();
        assertThat(numberOfModulesInLayer).isEqualTo(1);
    }

    @Test
    void classShouldBeLoadedFromModule() throws ClassNotFoundException {
        final ModuleLayer moduleLayer = Utils.createModuleLayer(pathToModules, Set.of(moduleName));
        final ClassLoader loader = moduleLayer.findLoader(moduleName);
        final Class<?> aClass = loader.loadClass("com.bartoszek.jtzclassloaderlibrary.RemoveVowels");
        final Class<?>[] interfaces = aClass.getInterfaces();
        for(Class c:interfaces){
            System.out.println(c.getName());
        }
    }


}
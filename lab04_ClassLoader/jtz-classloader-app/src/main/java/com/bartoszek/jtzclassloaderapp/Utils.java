package com.bartoszek.jtzclassloaderapp;

import java.io.IOException;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {
    public static List<String> getModulesNameFromPath(Path pathToModules) {
        return ModuleFinder.of(pathToModules).findAll().stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .collect(Collectors.toList());
    }

    public static ModuleLayer createModuleLayer(Path pathToModules, Set<String> rootModules) {
        ModuleFinder beforeFinder = ModuleFinder.of(pathToModules);
        ModuleFinder afterFinder = ModuleFinder.of();
        // Konfiguracja customowej warstwy
        Configuration parentConfig = ModuleLayer.boot().configuration();
        Configuration config = parentConfig.resolve(beforeFinder, afterFinder, rootModules);
        // Customowy ładowacz klas
        ClassLoader sysClassLoader = new CustomClassLoader(pathToModules);
        // Warstwa rodzicielska
        ModuleLayer parentLayer = ModuleLayer.boot();
        // Warstwa customowa
        ModuleLayer customLayer = parentLayer.defineModulesWithOneLoader(config, sysClassLoader);
        return customLayer;
    }

    public static List<String> getListOfClasses(Path pathToModules, String moduleName) throws IOException {
        Optional<ModuleReference> moduleReference = ModuleFinder.of(pathToModules).find(moduleName);
        if (moduleReference.isPresent()) {
            ModuleReference moduleReference1 = moduleReference.get();
            return moduleReference1.open().list()
                    .filter(e -> e.endsWith(".class"))
                    .filter(e -> !e.contains("processing/") && !e.contains("module-info.class"))
                    .map(e -> e.substring(0, e.lastIndexOf(".")))
                    .map(e -> e.replace("/", "."))
                    .collect(Collectors.toList());
        }
        return List.of();
    }
}
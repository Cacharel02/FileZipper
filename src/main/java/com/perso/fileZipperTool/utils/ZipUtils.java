package com.perso.fileZipperTool.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    /**
     * Zippe une liste de fichiers (nom + contenu binaire) et retourne le ZIP en byte[]
     *
     * @param fichiers map ou liste de paires (nom du fichier, contenu binaire)
     */
    public static byte[] zipFiles(List<FichierEntry> fichiers) throws IOException {

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zos = new ZipOutputStream(baos)) {

            for (FichierEntry fichier : fichiers) {
                ZipEntry entry = new ZipEntry(fichier.nom());
                zos.putNextEntry(entry);
                zos.write(fichier.contenu());
                zos.closeEntry();
            }

            zos.finish();
            return baos.toByteArray();
        }
    }

    public record FichierEntry(String nom, byte[] contenu) {}

}

package com.salomovs.carrental.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DbClient {
  public static List<String> read(String file, String header) {
    List<String> lines = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      reader.lines().forEach(line->{
        if (!line.equals(header)) lines.add(line);
      });
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return lines;
  }

  public static void write(String filename, String fileheader, List<String>  payload) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      writer.write(fileheader);
      writer.newLine();

      payload.forEach(line->{
        try {
          writer.append(line);
          writer.newLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void write(String filename, String payload) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
      writer.append(payload);
      writer.newLine();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
